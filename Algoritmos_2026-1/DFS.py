import copy
import matplotlib
import matplotlib.pyplot as plt
from matplotlib.widgets import Button
import networkx as nx

# Forzar backend interactivo
matplotlib.use("TkAgg")


def generate_dfs_history(G):
    """Ejecuta DFS y guarda una captura de pantalla del estado en cada paso."""
    # Línea 2-3 (DFS): Inicialización
    color = {v: "white" for v in G.nodes()}
    pi = {v: None for v in G.nodes()}
    d = {v: None for v in G.nodes()}
    f = {v: None for v in G.nodes()}
    t = 0

    history = []

    def save_step(desc):
        history.append(
            {
                "color": copy.deepcopy(color),
                "pi": copy.deepcopy(pi),
                "d": copy.deepcopy(d),
                "f": copy.deepcopy(f),
                "desc": desc,
            }
        )

    save_step("Estado Inicial")

    def dfs_visit(u):
        nonlocal t
        # Línea 2-3 (DFS-VISIT)
        color[u] = "gray"
        t += 1
        d[u] = t
        save_step(f"DFS-VISIT({u}): Descubierto {u} (Gris), d[{u}] = {t}")

        # Línea 4 (DFS-VISIT)
        for v in G.neighbors(u):
            # Línea 5 (DFS-VISIT)
            if color[v] == "white":
                # Línea 6 (DFS-VISIT)
                pi[v] = u
                dfs_visit(v)

        # Línea 10-11 (DFS-VISIT)
        color[u] = "black"
        t += 1
        f[u] = t
        save_step(f"DFS-VISIT({u}): Finalizado {u} (Negro), f[{u}] = {t}")

    # Línea 4-6 (DFS): Loop principal
    for u in G.nodes():
        if color[u] == "white":
            dfs_visit(u)

    return history


def interactive_dfs_visualizer(G):
    history = generate_dfs_history(G)
    current_step = [0]  # Usamos lista para mutabilidad en callbacks

    pos = nx.spring_layout(G, seed=42)

    fig, ax = plt.subplots(figsize=(9, 7))
    plt.subplots_adjust(bottom=0.2)

    def draw_state(step_idx):
        ax.clear()
        state = history[step_idx]

        node_colors = []
        for v in G.nodes():
            c = state["color"][v]
            if c == "white":
                node_colors.append("#FFFFFF")
            elif c == "gray":
                node_colors.append("#A0A0A0")
            elif c == "black":
                node_colors.append("#222222")

        nx.draw_networkx_edges(G, pos, ax=ax, edge_color="gray")
        nx.draw_networkx_nodes(
            G,
            pos,
            ax=ax,
            node_color=node_colors,
            edgecolors="black",
            node_size=800,
        )

        labels = {}
        for v in G.nodes():
            d_str = (
                "?" if state["d"][v] is None else str(state["d"][v])
            )
            f_str = (
                "?" if state["f"][v] is None else str(state["f"][v])
            )
            pi_str = (
                "NIL"
                if state["pi"][v] is None
                else str(state["pi"][v])
            )
            labels[v] = f"{v}\n{d_str}/{f_str}\nπ:{pi_str}"

        text_colors = {
            v: "white" if state["color"][v] == "black" else "black"
            for v in G.nodes()
        }
        for node, (x, y) in pos.items():
            ax.text(
                x,
                y,
                labels[node],
                fontsize=8,
                ha="center",
                va="center",
                color=text_colors[node],
                weight="bold",
            )

        ax.set_title(
            f"Paso {step_idx + 1}/{len(history)}: {state['desc']}",
            fontsize=11,
        )
        fig.canvas.draw_idle()

    # Botones de navegación
    ax_prev = plt.axes([0.25, 0.05, 0.2, 0.075])
    ax_next = plt.axes([0.55, 0.05, 0.2, 0.075])

    btn_prev = Button(ax_prev, "← Anterior")
    btn_next = Button(ax_next, "Siguiente →")

    def next_step(event):
        if current_step[0] < len(history) - 1:
            current_step[0] += 1
            draw_state(current_step[0])

    def prev_step(event):
        if current_step[0] > 0:
            current_step[0] -= 1
            draw_state(current_step[0])

    btn_next.on_clicked(next_step)
    btn_prev.on_clicked(prev_step)

    draw_state(0)
    plt.show()


if __name__ == "__main__":
    G = nx.Graph()
    G.add_edges_from(
        [
            ("u", "v"),
            ("u", "x"),
            ("v", "y"),
            ("x", "v"),
            ("w", "y"),
            ("y", "x"),
            ("w", "z"),
            ("z", "z"),
        ]
    )

    interactive_dfs_visualizer(G)
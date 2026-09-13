import time
from collections import deque
import matplotlib

matplotlib.use("TkAgg")  # Forzar backend interactivo
import matplotlib.pyplot as plt
import networkx as nx

# === CONFIGURACIÓN DE VELOCIDAD ===
SPEED = 1.0  # Segundos de pausa por paso (ej: 0.5 más rápido, 2.0 más lento)


def visualize_bfs(G, start_node):
    # Asignar posiciones fijas para los nodos
    pos = nx.spring_layout(G, seed=42)

    # 2-6: Inicialización de variables segun el pseudocódigo
    color = {v: "white" for v in G.nodes()}
    d = {v: float("inf") for v in G.nodes()}
    pi = {v: None for v in G.nodes()}

    # 7: Configuración del nodo inicial s
    color[start_node] = "gray"
    d[start_node] = 0
    pi[start_node] = None

    # 8: FIFO Queue
    Q = deque([start_node])

    # Configuración de matplotlib
    plt.ion()
    fig, ax = plt.subplots(figsize=(8, 6))

    def render(title):
        ax.clear()
        # Mapeo de colores visuales (para que 'white' sea visible en fondo blanco)
        node_colors = []
        for v in G.nodes():
            if color[v] == "white":
                node_colors.append("#FFFFFF")
            elif color[v] == "gray":
                node_colors.append("#A0A0A0")
            elif color[v] == "black":
                node_colors.append("#222222")

        # Dibujar grafo
        nx.draw_networkx_edges(G, pos, ax=ax, edge_color="gray")
        nx.draw_networkx_nodes(
            G,
            pos,
            ax=ax,
            node_color=node_colors,
            edgecolors="black",
            node_size=700,
        )

        # Etiquetas con Nodo, Distancia (d) y Ancestro (pi)
        labels = {}
        for v in G.nodes():
            dist_str = "∞" if d[v] == float("inf") else str(d[v])
            pi_str = "NIL" if pi[v] is None else str(pi[v])
            labels[v] = f"{v}\nd:{dist_str}\nπ:{pi_str}"

        # Color del texto según el fondo del nodo
        text_colors = {
            v: "white" if color[v] == "black" else "black" for v in G.nodes()
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

        ax.set_title(title)
        plt.draw()
        plt.pause(SPEED)

    render(f"Inicio BFS desde nodo {start_node}")

    # 9: Loop principal
    while len(Q) > 0:
        # 10: u <- HEAD(Q)
        u = Q[0]

        # 11: for each vertex v in Adj[u]
        for v in G.neighbors(u):
            # 12: if C_v == "White"
            if color[v] == "white":
                # 13: C_v <- "Gray", d_v <- d_u + 1, pi_v <- u
                color[v] = "gray"
                d[v] = d[u] + 1
                pi[v] = u
                # 14: ENQUEUE(Q, v)
                Q.append(v)
                render(f"Descubierto {v} desde {u} (Gris)")

        # 17: DEQUEUE(Q)
        Q.popleft()
        # 18: C_u <- "Black"
        color[u] = "black"
        render(f"Procesado completamente {u} (Negro)")

    plt.ioff()
    plt.show()


# === EJEMPLO DE USO ===
if __name__ == "__main__":
    # Crear un grafo de prueba
    G = nx.Graph()
    G.add_edges_from(
        [
            ("A", "B"),
            ("A", "C"),
            ("B", "D"),
            ("B", "E"),
            ("C", "F"),
            ("E", "F"),
            ("E", "G"),
        ]
    )

    visualize_bfs(G, start_node="A")
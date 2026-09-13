"""
Visualizador paso a paso del Algoritmo de Johnson (APSP)
==========================================================
Requisitos:
    pip install matplotlib

Uso:
    python johnson_visualizer.py

Controles:
    - Botones "<< Anterior" / "Siguiente >>" en la parte inferior
    - Flechas <- / -> del teclado
    - Home / End -> ir al primer / último paso
"""

import heapq
import math
import matplotlib.pyplot as plt
from matplotlib.widgets import Button
from matplotlib.patches import FancyArrowPatch, Circle

# ----------------------------------------------------------------------
# 1. DEFINICIÓN DEL GRAFO (edítalo aquí para probar otro grafo)
#    Debe ser conexo desde cualquier nodo hacia s y no tener ciclos
#    negativos, tal como lo requiere Johnson.
# ----------------------------------------------------------------------
VERTICES = [1, 2, 3, 4, 5]
EDGES = [
    (1, 2, 3),
    (1, 3, 8),
    (1, 5, -4),
    (2, 4, 1),
    (2, 5, 7),
    (3, 2, 4),
    (4, 1, 2),
    (4, 3, -5),
    (5, 4, 6),
]

INF = math.inf
S = "s"  # nodo fuente artificial que agrega Johnson

# ----------------------------------------------------------------------
# 2. CONSTRUCCIÓN DE LA SECUENCIA DE PASOS (frames)
#    Cada frame guarda todo lo necesario para dibujar ese instante:
#    título, texto explicativo, pesos a mostrar, nodos/aristas resaltadas,
#    valores h[], una tabla auxiliar y la matriz de distancias parcial.
# ----------------------------------------------------------------------
frames = []


def add_frame(title, text, weights, h=None, highlight_nodes=None,
              highlight_edges=None, dist_matrix=None, table=None):
    frames.append(dict(
        title=title,
        text=text,
        weights=dict(weights),
        h=dict(h) if h else None,
        highlight_nodes=set(highlight_nodes or []),
        highlight_edges=set(highlight_edges or []),
        dist_matrix={k: dict(v) for k, v in (dist_matrix or {}).items()},
        table=list(table) if table else None,
    ))


orig_weights = {(u, v): w for u, v, w in EDGES}

# ---- Paso 0: grafo original --------------------------------------------
add_frame("Grafo original",
          ["Este es el grafo dirigido de entrada G = (V, E).",
           "Puede tener pesos negativos, pero no ciclos negativos."],
          orig_weights)

# ---- Paso 1: agregar nodo fuente s --------------------------------------
aug_weights = dict(orig_weights)
for v in VERTICES:
    aug_weights[(S, v)] = 0
add_frame("Paso 1: agregar nodo fuente s",
          ["Se crea un nuevo nodo s con una arista de peso 0",
           "hacia cada uno de los demas vertices.",
           "Esto no altera las distancias relativas entre los nodos originales."],
          aug_weights, highlight_nodes=[S])

# ---- Paso 2: Bellman-Ford desde s para calcular h[v] --------------------
verts_aug = [S] + VERTICES
dist = {v: INF for v in verts_aug}
dist[S] = 0
add_frame("Paso 2: Bellman-Ford desde s (inicializacion)",
          ["Inicializamos dist[s] = 0 y dist[v] = infinito para los demas.",
           "Vamos a relajar todas las aristas |V| veces como maximo."],
          aug_weights, h=dist)

aug_edges = [(S, v, 0) for v in VERTICES] + EDGES
for i in range(1, len(verts_aug)):
    changed = False
    for (u, v, w) in aug_edges:
        if dist[u] != INF and dist[u] + w < dist[v]:
            dist[v] = dist[u] + w
            changed = True
    resumen = ", ".join(f"h[{v}]={dist[v]}" for v in VERTICES)
    add_frame(f"Paso 2: Bellman-Ford - ronda {i}",
              [f"Ronda {i} de relajacion de todas las aristas.",
               "Valores actuales: " + resumen,
               "Hubo cambios en esta ronda." if changed else "Sin cambios: ya convergio."],
              aug_weights, h=dist)
    if not changed:
        break

h = {v: dist[v] for v in VERTICES}
add_frame("Paso 2 (resultado): h[v] = delta(s, v)",
          ["Estos valores h[v] se usan para reponderar las aristas.",
           "h = " + ", ".join(f"h[{v}]={h[v]}" for v in VERTICES)],
          aug_weights, h=h, highlight_nodes=[S])

# ---- Paso 3: reponderar las aristas --------------------------------------
reweighted = {}
for (u, v, w) in EDGES:
    wprime = w + h[u] - h[v]
    reweighted[(u, v)] = wprime
    add_frame(f"Paso 3: reponderar arista ({u} -> {v})",
              [f"w^({u},{v}) = w({u},{v}) + h({u}) - h({v})",
               f"           = {w} + {h[u]} - {h[v]} = {wprime}"],
              {**orig_weights, **reweighted}, h=h,
              highlight_edges=[(u, v)])

add_frame("Paso 3 (resultado): grafo reponderado",
          ["Todas las aristas del grafo reponderado w^ tienen peso >= 0.",
           "Ahora podemos ejecutar Dijkstra desde cada vertice."],
          reweighted, h=h)

# ---- Paso 4: Dijkstra desde cada vertice ---------------------------------
adj = {v: [] for v in VERTICES}
for (u, v, w) in EDGES:
    adj[u].append((v, reweighted[(u, v)]))

full_matrix = {u: {} for u in VERTICES}

for src in VERTICES:
    dhat = {v: INF for v in VERTICES}
    dhat[src] = 0
    visited = set()
    pq = [(0, src)]

    resumen = ", ".join(f"{v}:{'inf' if dhat[v] == INF else dhat[v]}" for v in VERTICES)
    add_frame(f"Dijkstra desde el vertice {src}",
              [f"Ejecutamos Dijkstra en el grafo reponderado w^, con fuente = {src}.",
               "Distancias iniciales: " + resumen],
              reweighted, h=h, highlight_nodes=[src], dist_matrix=full_matrix)

    while pq:
        d_u, u = heapq.heappop(pq)
        if u in visited:
            continue
        visited.add(u)
        relaxed = []
        for (v, w) in adj[u]:
            if v not in visited and dhat[u] + w < dhat[v]:
                dhat[v] = dhat[u] + w
                heapq.heappush(pq, (dhat[v], v))
                relaxed.append(v)
        resumen = ", ".join(f"{v}:{'inf' if dhat[v] == INF else dhat[v]}" for v in VERTICES)
        detalle = f"Actualizados: {relaxed}" if relaxed else "Sin cambios."
        add_frame(f"Dijkstra({src}): extraer vertice {u}",
                  [f"Se extrae el vertice {u} con distancia minima w^ = {d_u}.",
                   f"Se relajan sus aristas salientes. {detalle}",
                   "Distancias actuales: " + resumen],
                  reweighted, h=h, highlight_nodes=[u] + relaxed,
                  highlight_edges=[(u, v) for (v, _) in adj[u]],
                  dist_matrix=full_matrix)

    # convertir de vuelta a distancias reales: d(u,v) = dhat(u,v) + h(v) - h(u)
    table_rows = []
    for v in VERTICES:
        if dhat[v] == INF:
            real = INF
        else:
            real = dhat[v] + h[v] - h[src]
        table_rows.append((src, v, dhat[v], h[v], h[src], real))
        full_matrix[src][v] = real
    add_frame(f"Dijkstra({src}): convertir a distancias reales",
              [f"d(u,v) = delta^(u,v) + h(v) - h(u), con u = {src}.",
               "Se muestran los calculos en la tabla de la derecha."],
              orig_weights, h=h, table=table_rows, dist_matrix=full_matrix)

# ---- Paso final: matriz resumen ------------------------------------------
add_frame("Resultado final: matriz de distancias APSP",
          ["Esta es la matriz D con las distancias mas cortas entre",
           "todos los pares de vertices: O(V*E + V^2 log V) en total."],
          orig_weights, h=h, dist_matrix=full_matrix)

# ----------------------------------------------------------------------
# 3. DIBUJO / INTERFAZ
# ----------------------------------------------------------------------


def layout(f):
    n = len(VERTICES)
    pos = {}
    for i, v in enumerate(VERTICES):
        angle = 2 * math.pi * i / n + math.pi / 2
        pos[v] = (math.cos(angle), math.sin(angle))
    if any(u == S or v == S for (u, v) in f["weights"]):
        pos[S] = (-1.9, 0.0)
    return pos


def draw_edge(ax, p1, p2, w, color, lw):
    dx, dy = p2[0] - p1[0], p2[1] - p1[1]
    dist = math.hypot(dx, dy)
    if dist == 0:
        return
    ux, uy = dx / dist, dy / dist
    r = 0.15
    start = (p1[0] + ux * r, p1[1] + uy * r)
    end = (p2[0] - ux * r, p2[1] - uy * r)
    rad = 0.15
    arrow = FancyArrowPatch(start, end, connectionstyle=f"arc3,rad={rad}",
                             arrowstyle="-|>", mutation_scale=14,
                             color=color, lw=lw, zorder=2)
    ax.add_patch(arrow)
    mx, my = (start[0] + end[0]) / 2, (start[1] + end[1]) / 2
    perp = (-uy, ux)
    mx += perp[0] * rad * 0.7
    my += perp[1] * rad * 0.7
    ax.text(mx, my, str(w), fontsize=9, color=color, ha="center", va="center",
            bbox=dict(boxstyle="round,pad=0.1", fc="white", ec="none", alpha=0.85),
            zorder=5)


def render(idx):
    f = frames[idx]
    ax_graph.clear()
    ax_info.clear()
    ax_graph.set_xlim(-2.3, 1.6)
    ax_graph.set_ylim(-1.6, 1.6)
    ax_graph.set_aspect("equal")
    ax_graph.axis("off")

    pos = layout(f)
    for (u, v), w in f["weights"].items():
        if u not in pos or v not in pos:
            continue
        highlighted = (u, v) in f["highlight_edges"]
        color = "crimson" if highlighted else "gray"
        lw = 2.6 if highlighted else 1.2
        draw_edge(ax_graph, pos[u], pos[v], w, color, lw)

    for v in pos:
        if v in f["highlight_nodes"]:
            color = "gold"
        elif v == S:
            color = "lightcoral"
        else:
            color = "lightskyblue"
        circ = Circle(pos[v], 0.14, facecolor=color, edgecolor="black", zorder=3)
        ax_graph.add_patch(circ)
        ax_graph.text(pos[v][0], pos[v][1], str(v), ha="center", va="center",
                       fontweight="bold", zorder=4)
        if f["h"] and v in f["h"]:
            hv = f["h"][v]
            htxt = "inf" if hv == INF else hv
            ax_graph.text(pos[v][0], pos[v][1] - 0.26, f"h={htxt}",
                           ha="center", fontsize=8, color="darkgreen")

    ax_graph.set_title(f["title"], fontsize=13, fontweight="bold")

    ax_info.axis("off")
    y = 0.97
    for line in f["text"]:
        ax_info.text(0.02, y, line, fontsize=10, transform=ax_info.transAxes)
        y -= 0.07

    if f["table"]:
        y -= 0.04
        ax_info.text(0.02, y, "src  dest   dhat   h[v]  h[u]  d_real",
                      fontsize=9, family="monospace", fontweight="bold",
                      transform=ax_info.transAxes)
        y -= 0.045
        for (src, v, dh, hv, hu, real) in f["table"]:
            dh_s = "inf" if dh == INF else dh
            real_s = "inf" if real == INF else real
            row = f"{src:>3}  {v:>4}   {dh_s:>4}  {hv:>4}  {hu:>4}  {real_s:>6}"
            ax_info.text(0.02, y, row, fontsize=9, family="monospace",
                          transform=ax_info.transAxes)
            y -= 0.04

    if f["dist_matrix"]:
        y -= 0.05
        ax_info.text(0.02, y, "Matriz de distancias (parcial):",
                      fontsize=10, fontweight="bold", transform=ax_info.transAxes)
        y -= 0.045
        header = "     " + "".join(f"{v:>6}" for v in VERTICES)
        ax_info.text(0.02, y, header, fontsize=9, family="monospace",
                      transform=ax_info.transAxes)
        y -= 0.04
        for u in VERTICES:
            row = f"{u:>3}: "
            for v in VERTICES:
                if v in f["dist_matrix"].get(u, {}):
                    row += f"{f['dist_matrix'][u][v]:>6}"
                else:
                    row += f"{'.':>6}"
            ax_info.text(0.02, y, row, fontsize=9, family="monospace",
                          transform=ax_info.transAxes)
            y -= 0.04

    fig.suptitle(f"Algoritmo de Johnson - paso {idx + 1}/{len(frames)}", fontsize=11)
    fig.canvas.draw_idle()


current = [0]


def go_to(idx):
    idx = max(0, min(len(frames) - 1, idx))
    if idx != current[0]:
        current[0] = idx
        render(current[0])


def next_step(event=None):
    go_to(current[0] + 1)


def prev_step(event=None):
    go_to(current[0] - 1)


def on_key(event):
    if event.key == "right":
        next_step()
    elif event.key == "left":
        prev_step()
    elif event.key == "home":
        go_to(0)
    elif event.key == "end":
        go_to(len(frames) - 1)


fig = plt.figure(figsize=(13, 7))
gs = fig.add_gridspec(1, 2, width_ratios=[1.35, 1])
ax_graph = fig.add_subplot(gs[0])
ax_info = fig.add_subplot(gs[1])
plt.subplots_adjust(bottom=0.13, top=0.88)

ax_prev = plt.axes([0.35, 0.02, 0.13, 0.06])
ax_next = plt.axes([0.52, 0.02, 0.13, 0.06])
btn_prev = Button(ax_prev, "<< Anterior")
btn_next = Button(ax_next, "Siguiente >>")
btn_prev.on_clicked(prev_step)
btn_next.on_clicked(next_step)
fig.canvas.mpl_connect("key_press_event", on_key)

render(0)
plt.show()
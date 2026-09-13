"""
Visualizador paso a paso del Algoritmo de Floyd-Warshall (APSP)
==================================================================
Requisitos:
    pip install matplotlib

Uso:
    python floyd_warshall_visualizer.py

Controles:
    - Botones "<< Anterior" / "Siguiente >>" en la parte inferior
    - Flechas <- / -> del teclado
    - Home / End -> ir al primer / ultimo paso

Por cada celda d[i][j] que se procesa, el panel derecho explica el
calculo exacto (min entre el valor actual y pasar por k) y por que
se actualiza o se conserva el valor, ademas de la matriz de
predecesores Pi que se va reconstruyendo en paralelo.
"""

import math
import textwrap
import matplotlib.pyplot as plt
from matplotlib.widgets import Button
from matplotlib.patches import Rectangle

# ----------------------------------------------------------------------
# 1. DEFINICION DEL GRAFO (edita esto para probar otro grafo)
#    W[i][j] = peso de la arista i->j, math.inf si no existe.
# ----------------------------------------------------------------------
INF = math.inf
VERTICES = [1, 2, 3, 4, 5]

W = {
    1: {1: 0,   2: 3,   3: 8,   4: INF, 5: -4},
    2: {1: INF, 2: 0,   3: INF, 4: 1,   5: 7},
    3: {1: INF, 2: 4,   3: 0,   4: INF, 5: INF},
    4: {1: 2,   2: INF, 3: -5,  4: 0,   5: INF},
    5: {1: INF, 2: INF, 3: INF, 4: 6,   5: 0},
}

"""
W = {
    1: {1: 0, 2: INF, 3: INF, 4: 3},
    2: {1: 4, 2: 0, 3: 8, 4: INF},
    3: {1: 3, 2: INF, 3: 0, 4: 6}, 
    4: {1: INF, 2: 5, 3: 1, 4: 0},
}
"""

# ----------------------------------------------------------------------
# 2. CONSTRUCCION DE LA SECUENCIA DE PASOS (frames)
# ----------------------------------------------------------------------
frames = []


def add_frame(title, text, D, Pi, cell=None, k=None):
    frames.append(dict(
        title=title,
        text=list(text),
        D={i: dict(row) for i, row in D.items()},
        Pi={i: dict(row) for i, row in Pi.items()},
        cell=cell,          # (i, j) resaltado, o None
        k=k,                # vertice intermedio actual, o None
    ))


def fmt(x):
    return "inf" if x == INF else str(x)


# ---- D(0) y Pi(0) ---------------------------------------------------------
D = {i: dict(W[i]) for i in VERTICES}
Pi = {}
for i in VERTICES:
    Pi[i] = {}
    for j in VERTICES:
        if i == j or W[i][j] == INF:
            Pi[i][j] = None
        else:
            Pi[i][j] = i

add_frame("Matriz inicial D(0)",
          ["D(0) es simplemente la matriz de pesos W del grafo.",
           "d[i][j] = 0 si i=j, w(i,j) si existe la arista, o infinito si no.",
           "Pi(0)[i][j] = i si existe la arista i->j (i!=j), si no, vacio."],
          D, Pi)

# ---- Rondas k = 1..n -------------------------------------------------------
for k in VERTICES:
    add_frame(f"Inicio de la ronda k = {k}",
              [f"Ahora permitimos que el vertice {k} sea un vertice intermedio",
               "en cualquier camino. Vamos a revisar cada par (i, j) y ver si",
               f"pasar por {k} genera un camino mas corto que el que ya tenemos."],
              D, Pi, k=k)

    for i in VERTICES:
        for j in VERTICES:
            old = D[i][j]
            dik = D[i][k]
            dkj = D[k][j]
            via = INF if (dik == INF or dkj == INF) else dik + dkj
            new = min(old, via)
            updated = new < old

            trivial = (i == k or j == k)

            lines = [
                f"d[{i}][{j}]  =  min( d[{i}][{j}] , d[{i}][{k}] + d[{k}][{j}] )",
                f"        =  min( {fmt(old)} , {fmt(dik)} + {fmt(dkj)} )",
                f"        =  min( {fmt(old)} , {fmt(via)} )",
                f"        =  {fmt(new)}",
            ]

            if trivial:
                lines.append(f"Como i=k o j=k, este valor no puede cambiar (es trivial).")
            elif updated:
                lines.append(
                    f"DECISION: pasar por {k} es mas corto "
                    f"({fmt(via)} < {fmt(old)}). Se ACTUALIZA d[{i}][{j}] y "
                    f"Pi[{i}][{j}] = Pi[{k}][{j}] = {Pi[k][j]}."
                )
            else:
                if via == INF:
                    lines.append(
                        f"DECISION: no existe camino de {i} a {j} pasando por {k} "
                        f"(al menos uno de los tramos es infinito). Se conserva d[{i}][{j}]."
                    )
                else:
                    lines.append(
                        f"DECISION: el camino actual ({fmt(old)}) ya es igual o mejor que "
                        f"pasar por {k} ({fmt(via)}). Se conserva d[{i}][{j}] y Pi[{i}][{j}]."
                    )

            D[i][j] = new
            if updated:
                Pi[i][j] = Pi[k][j]

            add_frame(f"k={k}: calculando d[{i}][{j}]", lines, D, Pi, cell=(i, j), k=k)

    add_frame(f"Fin de la ronda k = {k}: D({k})",
              [f"Esta es la matriz D({k}) completa, permitiendo como vertices",
               f"intermedios solo a {{1,...,{k}}}.",
               "Pasamos al siguiente valor de k (o terminamos si era el ultimo)."],
              D, Pi, k=k)

# ---- Resultado final --------------------------------------------------------
neg_cycle = any(D[i][i] < 0 for i in VERTICES)
final_text = [
    "Esta es la matriz final D(n) con las distancias mas cortas entre",
    "todos los pares de vertices.",
]
if neg_cycle:
    negs = [i for i in VERTICES if D[i][i] < 0]
    final_text.append(f"ATENCION: d[i][i] < 0 para i en {negs} -> hay un ciclo negativo.")
else:
    final_text.append("Ningun d[i][i] es negativo, asi que no hay ciclos negativos.")

add_frame("Resultado final: matriz de distancias APSP", final_text, D, Pi)

# ----------------------------------------------------------------------
# 3. DIBUJO / INTERFAZ
# ----------------------------------------------------------------------
n = len(VERTICES)
CELL = 1.0


def draw_matrix(ax, M, title, cell_hi=None, row_hi=None, col_hi=None, is_pi=False):
    ax.clear()
    ax.set_xlim(-0.6, n + 0.2)
    ax.set_ylim(-0.6, n + 1.0)
    ax.set_aspect("equal")
    ax.axis("off")
    ax.set_title(title, fontsize=11, fontweight="bold")

    # encabezados de columna
    for jx, j in enumerate(VERTICES):
        ax.text(jx + 0.5, n + 0.35, str(j), ha="center", va="center",
                 fontweight="bold", fontsize=9)
    # encabezados de fila
    for ix, i in enumerate(VERTICES):
        ax.text(-0.3, n - ix - 0.5, str(i), ha="center", va="center",
                 fontweight="bold", fontsize=9)

    for ix, i in enumerate(VERTICES):
        for jx, j in enumerate(VERTICES):
            x, y = jx, n - ix - 1
            face = "white"
            edge = "black"
            lw = 0.8
            if row_hi is not None and i == row_hi:
                face = "#dff0ff"
            if col_hi is not None and j == col_hi:
                face = "#e5ffe0"
            if cell_hi is not None and (i, j) == cell_hi:
                face = "#ffe97a"
                lw = 2.2
            rect = Rectangle((x, y), CELL, CELL, facecolor=face, edgecolor=edge, lw=lw)
            ax.add_patch(rect)
            val = M[i][j]
            if is_pi:
                txt = "-" if val is None else str(val)
            else:
                txt = fmt(val)
            ax.text(x + 0.5, y + 0.5, txt, ha="center", va="center", fontsize=10)


def render(idx):
    f = frames[idx]
    cell = f["cell"]
    k = f["k"]

    draw_matrix(ax_D, f["D"], "Matriz D (distancias)",
                cell_hi=cell, row_hi=k, col_hi=k, is_pi=False)
    draw_matrix(ax_Pi, f["Pi"], "Matriz Pi (predecesores)",
                cell_hi=cell, row_hi=k, col_hi=k, is_pi=True)

    ax_info.clear()
    ax_info.axis("off")
    y = 0.95
    for wline in textwrap.wrap(f["title"], width=50) or [""]:
        ax_info.text(0.02, y, wline, fontsize=13, fontweight="bold",
                      transform=ax_info.transAxes)
        y -= 0.07
    y -= 0.03
    for line in f["text"]:
        is_formula = line.strip().startswith(("d[", "=")) or "min(" in line
        if is_formula:
            ax_info.text(0.02, y, line, fontsize=10.5, transform=ax_info.transAxes,
                          family="monospace")
            y -= 0.06
        else:
            wrapped = textwrap.wrap(line, width=52) or [""]
            for wline in wrapped:
                ax_info.text(0.02, y, wline, fontsize=10.5, transform=ax_info.transAxes)
                y -= 0.055
            y -= 0.015

    fig.suptitle(f"Algoritmo de Floyd-Warshall - paso {idx + 1}/{len(frames)}", fontsize=11)
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


fig = plt.figure(figsize=(14, 7.5))
gs = fig.add_gridspec(1, 3, width_ratios=[0.95, 0.8, 1.35])
ax_D = fig.add_subplot(gs[0])
ax_Pi = fig.add_subplot(gs[1])
ax_info = fig.add_subplot(gs[2])
plt.subplots_adjust(bottom=0.13, top=0.88, wspace=0.35)

ax_prev = plt.axes([0.35, 0.02, 0.13, 0.06])
ax_next = plt.axes([0.52, 0.02, 0.13, 0.06])
btn_prev = Button(ax_prev, "<< Anterior")
btn_next = Button(ax_next, "Siguiente >>")
btn_prev.on_clicked(prev_step)
btn_next.on_clicked(next_step)
fig.canvas.mpl_connect("key_press_event", on_key)

render(0)
plt.show()
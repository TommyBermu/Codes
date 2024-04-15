from taller import redimensionar, reflejar_vertical, reflejar_horizontal, crear_collage

def test_redimensionar():
    assert redimensionar([1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],4) == [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]

def test_reflejar_vertical():
    assert reflejar_vertical([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]) == [[9, 10, 11, 12], [5, 6, 7, 8], [1, 2, 3, 4]]

def test_reflejar_horizontal():
    assert reflejar_horizontal([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]]) == [[4, 3, 2, 1], [8, 7, 6, 5], [12, 11, 10, 9]]

def test_crear_collage():
    assert crear_collage([[1, 2], [3, 4]]) == [[1, 2, 2, 1], [3, 4, 4, 3], [3, 4, 4, 3], [1, 2, 2, 1]]
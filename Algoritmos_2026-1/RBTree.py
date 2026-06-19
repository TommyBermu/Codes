RED = "RED"
BLACK = "BLACK"

class Node:
    def __init__(self, key, color=RED):
        self.key = key
        self.color = color # se crea como rojo por defecto
        self.left = None
        self.right = None
        self.p = None

class RBTree:
    def __init__(self):
        self.nil = Node(None, BLACK) # centinela
        self.nil.left = self.nil
        self.nil.right = self.nil
        self.nil.p = self.nil
        self.root = self.nil

    def leftRotate(self, x):
        y = x.right
        x.right = y.left
        
        if y.left != self.nil:
            y.left.p = x
            
        y.p = x.p
        
        if x.p == self.nil:
            self.root = y
        elif x == x.p.left:
            x.p.left = y
        else:
            x.p.right = y
            
        y.left = x
        x.p = y

    def rightRotate(self, y):
        x = y.left
        y.left = x.right
        
        if x.right != self.nil:
            x.right.p = y
            
        x.p = y.p
        
        if y.p == self.nil:
            self.root = x
        elif y == y.p.right:
            y.p.right = x
        else:
            y.p.left = x
            
        x.right = y
        y.p = x

    def insert(self, key):
        z = Node(key)
        y = self.nil
        x = self.root
        
        while x != self.nil:
            y = x
            if z.key < x.key:
                x = x.left
            else:
                x = x.right
                
        z.p = y
        if y == self.nil:
            self.root = z
        elif z.key < y.key:
            y.left = z
        else:
            y.right = z
            
        z.left = self.nil
        z.right = self.nil
        
        self.insertFixup(z)

    def insertFixup(self, z):
        while z.p.color == RED:
            if z.p == z.p.p.left: # caso 1,2,3
                y = z.p.p.right
                
                if y.color == RED: # caso 1
                    z.p.color = BLACK
                    y.color = BLACK
                    z.p.p.color = RED
                    z = z.p.p
                else:
                    if z == z.p.right: # caso 2
                        z = z.p
                        self.leftRotate(z)
                    
                    # caso 3
                    z.p.color = BLACK
                    z.p.p.color = RED
                    self.rightRotate(z.p.p)
                    
            else: # caso 4,5,6
                y = z.p.p.left
                
                if y.color == RED: # caso 4
                    z.p.color = BLACK
                    y.color = BLACK
                    z.p.p.color = RED
                    z = z.p.p
                else:
                    if z == z.p.left: # caso 5
                        z = z.p
                        self.rightRotate(z)
                    
                    # caso 6
                    z.p.color = BLACK
                    z.p.p.color = RED
                    self.leftRotate(z.p.p)
                    
        self.root.color = BLACK

    def inorder(self, x):
        if x != self.nil:
            self.inorder(x.left)
            print(x.key, end=' ')
            self.inorder(x.right)

tree = RBTree()
keys = [10, 20, 30, 15, 25, 5]

for key in keys:
    tree.insert(key)

print("Árbol Rojo-Negro:")
tree.inorder(tree.root)
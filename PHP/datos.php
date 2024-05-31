<?php

echo "Hola Mundo!\n"; // ya le sabes que significa el "\n"

$my_string = "Esto es una cadena de texto";

echo $my_string . "\n"; // la concatenación de Strings es con un . 
echo "esto es una prueba\n";

$my_string = "Aqui cambio la cadena de texto";
echo $my_string . "\n";

echo gettype($my_string) . "\n"; // imprime "string"

$my_string = "Hola"; // como es de tipado dinamico, se puede cambiar de string a integer
echo $my_string . "\n"; // tambien se puede concatenar integer y string

echo gettype($my_string) . "\n";

$my_integer = 7;
$my_integer += 4;

echo $my_integer . "\n";
echo gettype($my_integer) . "\n";

$my_double = 5.47;
echo $my_double . "\n";
echo gettype($my_double) . "\n";

echo $my_string . $my_integer . "\n" . $my_double . "\n";

$my_boolean = true;
echo $my_boolean == 0; // si es 0 no imprime nada, si es 1 imprime 1
echo "\n"; 
echo gettype($my_boolean) . "\n";

echo "el valor de mi integer es $my_integer, y el de mi double es $my_double," . "\n"; // para concatenar no hay que hacer mucho xd

var_dump($my_double);

// constants

const MY_STRING = "Esto es una constante";

echo MY_STRING . "\n"; // para una constante no hay que colocar el singo de dolar

// arrays

$my_array = [$my_string, $my_integer, $my_double];

echo gettype($my_array) . "\n";
echo $my_array[0] . "\n";
echo $my_array[2] . "\n";

array_push($my_array, $my_boolean); 

print_r($my_array);

// Dictionary 

$my_dicc = array("string" => $my_string, "integer" => $my_integer, "double" => $my_double, "boolean" => $my_boolean);

echo gettype($my_dicc);
print_r($my_dicc);
echo $my_dicc["integer"] . "\n";

// loops

for ($index = 0; $index <= 10; $index++) {
    echo $index . "\n";
}

foreach($my_array as $item) {
    echo $item . "\n";
}

$a = 0;
while ($a <= sizeof($my_array) -1) {
    echo $my_array[$a] . "\n";
    $a++;
}

if ($my_integer == 11 && $my_string == "Hola") {
    echo "el valor es 11 \n";
} elseif ($my_integer == 12 || $my_string == "Hola") {
    echo "el valor es 12 \n";
} else {
    echo "el valor no es 11 ni 12";
}

// functions

function print_number(int $n) {
    echo $n . "\n";
}

print_number(40);
print_number(10.2); // solo se queda con la parte entera
//print_number("Hola"); // da error porque la función está "tipada fuertemente" a int

//classes

class MyClass {
    public $name;
    public $age;

    function __construct(string $name, int $age) { // función constructora xd
        $this->name = $name;
        $this->age = $age;
    }
}

$my_object = new MyClass("Tomás", 18);
print_r($my_object);
echo $my_object->name . "\n";

$my_object->name = "Alejandro";
echo $my_object->name . "\n";
echo gettype($my_object) . "\n";
?>
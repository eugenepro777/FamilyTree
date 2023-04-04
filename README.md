# Урок 1. Принципы ООП: Инкапсуляция, наследование, полиморфизм
Реализовать, с учетом ооп подхода, приложение.
Для проведения исследований с генеалогическим древом.
Идея: описать некоторое количество компонент, например:
модель человека и дерева
Под “проведением исследования” можно понимать например получение всех детей выбранного человека.

# Урок 2. Принципы ООП Абстракция и интерфейсы. Пример проектирования
В проекте с генеалогическим древом подумайте и используйте интерфейсы.
Дополнить проект методами записи в файл и чтения из файла. Для этого создать отдельный класс
и реализовать в нем нужные методы.
Для данного класса сделайте интерфейс, который и используйте в своей программе. 
НАПРИМЕР в классе дерева в качестве аргумента метода save передавайте не конкретный класс,
а объект интерфейса, с помощью которого и будет происходить запись.

Продолжаем работать с проектом с семейным деревом.
Реализовать интерфейс Iterable для дерева.
Создать методы сортировки списка людей перед выводом,
например по имени или по дате рождения (не менее 2)
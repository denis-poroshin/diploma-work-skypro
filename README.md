<h2>1. Содержание проекта</h2>
Учебный проект в котором реализована бэкенд часть сайта по перепродаже вещей.
<h2>2. Стек технологий </h2>
    Java
    Maven
    Spring Boot
    Spring Security
    Spring Data
    PostgreSQL
<h2>3. Учавстовали в проекте</h2>
Андрей Левин, Порошин Денис, Чингис Адгишев, Лиана Петросян
<h2>4. Запуск приложения</h2>
Для развертывания необходимы приложения базы данных PostgreSQL и H2, среда разработки IntelliJ IDEA, Java 11.
Необходимо клонировать приложение себе на ПК. Это можно сделать с помощью git. Открываем документ, который хотим клонировать проект, нажимаем правую кнопку мыши на пустое место в документе и выбираем:

![image](https://github.com/user-attachments/assets/9fd182ff-76ea-435c-ab01-38aec9685cda)

Ссылку проекта можно взять в git:

![image](https://github.com/user-attachments/assets/b87f4ec5-fd5a-4e55-965c-3e5850c43656)

Откроется консоль, в которой необходимо прописать (git clone https://github.com/denis-poroshin/diploma-work-skypro.git):

![image](https://github.com/user-attachments/assets/ca8b1db4-ae10-4dee-ac52-f946adda1fd4)

После клонирования репозитория необходимо запустить среду разработки и открыть там файл.

После этого необходимо создать базу данных в PostgreSQL с названием bank-star, ролью employee и паролем qwer. Также все параметры по созданию базы данных можно поменять, но при этом не забыть поменять и в самом application.properties:

![image](https://github.com/user-attachments/assets/c09dba9e-5c6c-44a0-ae92-a2104b901e09)

Для запуска фонтент части необходимо в консоли прописать: docker run -p 3000:3000 --rm ghcr.io/dmitry-bizin/front-react-avito:v1.21

<h2>5. Сборка проекта</h2>
В правом верхнем углу розетки требуется буква m. В Spring-Bank-Star необходимо нажать на чистую упаковку, а затем на посылку. Сначала определите цель, а затем выберите проект в jar фал.

![image](https://github.com/user-attachments/assets/294da437-7582-41b4-a9bc-d5286bf6a1f6)






# HelloJSON

Ficha laboratorial 2 de Engenharia de Software (Iscte) - Parte A.

Projeto Maven que usa a biblioteca Jackson para converter objetos Java em JSON e vice-versa.
O `main()` gera uma lista de alunos americanos imaginarios (numero + nome) no ficheiro `data/students.cvs`,
com nomes tirados dos ficheiros JSON do projeto [American Names](https://github.com/rossgoodwin/american-names).

## Compilar e correr

```
mvn clean package
java -jar target/HelloJSON-1.0-SNAPSHOT.jar
```

O jar e gerado com o `maven-shade-plugin`, por isso ja inclui as dependencias e o `MANIFEST.MF` com a classe main.

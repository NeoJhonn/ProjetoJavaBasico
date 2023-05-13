import builders.StudentsBuilder;
import entities.Student;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var allStudents = StudentsBuilder.getAllStudents();

        // Agora vamos as atividades
        /*
        1. Recupere da lista os alunos que passaram de ano (nota minima 7.0).
            - Exiba os dados nesse formato: <código> - <nome> : Média = <nota>
        */
        aprovedStudents(allStudents);

        /*
        2. Recupere da lista os alunos que não passaram de ano.
            - Exiba os dados nesse formato: <código> - <nome> : Média = <media> (Faltou = <nota_faltante>)
        */
        failedStudents(allStudents);

        /*
        3. Traga os alunos que tiraram a nota máxima (nota 10).
            - Exiba os dados nesse formato: <código> - <nome>
        */
        studentsWithMaxGrade(allStudents);

        /*
        4. Traga o aluno que tirou a menor nota, em caso de notas iguais, traga ambos os alunos.
            - Exiba os dados nesse formato: <código> - <nome> : Nota = <nota>
        */
        studentsWithLowestGrade(allStudents);

        /*
        5. Faça uma lista com top 3 notas de alunos. Em caso de notas iguais coloque todos na mesma posição.
            - Ex:
                1º - Fulano : Nota = 10.0;
                   - Beltrano : Nota = 10.0;
                2º - Joãozinho : Nota = 9.0;
                3º - Mariazinha : Nota = 8.9;
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        */
        topTreeGradesOfTheStudents(allStudents);

        /*
        6. Faça uma lista com as 3 menores notas de alunos. Em caso de notas iguais coloque todos na mesma posição. Exemplo igual a anterior
            - Exiba os dados nesse formato: <posicao> - <nome> : Nota = <nota>
        */
        threeLowestGrades(allStudents);

        /*
        7. Monte a média de todos os alunos e exiba em tela ordenando da maior para a menor nota.
            - Exiba os dados nesse formato: <posicao> - <código> - <nome> : Média = <nota>
         */
        averageOfAllStudents(allStudents);
    }

    public static void aprovedStudents(List<Student> students){
        float average = 0;

        System.out.println("Alunos que passaram de ano (nota minima 7.0):");
        for (Student st : students){
            average = (st.getTestOne() + st.getTestTwo() + st.getTestThree()) / 3;
            if (average >= 7.0f){
                System.out.println(st.getCode()+ " - "+st.getName()+" : Média = "+average);
            }
        }
        System.out.println("\n");
    }

    public static void failedStudents(List<Student> students){
        float average = 0;
        float gap = 0;

        System.out.println("Alunos que não passaram de ano:");
        for (Student st : students){
            average = (st.getTestOne() + st.getTestTwo() + st.getTestThree()) / 3;
            if (average < 7.0f){
                gap = 7.0f - average;
                System.out.println(st.getCode()+ " - "+st.getName()+" : Média = "+ average +"(Faltou = "+ gap +")");
            }
        }
        System.out.println("\n");
    }

    public static void studentsWithMaxGrade(List<Student> students){
        System.out.println("Alunos que tiraram a nota máxima (nota 10):");
        for (Student st : students){
            String temp = "";
            if(st.getTestOne() == 10.0f){
                temp = " (Teste 1) ";
            }
            if (st.getTestTwo() == 10.0f) {
                    temp += " (Teste 2) ";
            }
            if (st.getTestThree() == 10.0f) {
                    temp += " (Teste 3) ";
            }
            if(!temp.equals("")){
                System.out.println(st.getCode() +" - "+ st.getName() + temp);
            }

            //limpar variável para o próximo loop
            temp = "";
        }
        System.out.println("\n");
    }

    public static void studentsWithLowestGrade(List<Student> students){
        float min = 0;
        List<Float> grades = new ArrayList<>();

        //encontrar e colocar numa lista a menor nota de cada aluno
        for (Student st : students){
            min = st.getTestOne();
            if(st.getTestTwo() < min){
                min = st.getTestTwo();
            }
            if(st.getTestThree() < min){
                min = st.getTestThree();
            }
            grades.add(min);
        }

        //encontrar a menor nota na lista de notas
        min = grades.get(0);
        for (Float g : grades){
            if(g < min ){
                min = g;
            }
        }

        //encontra os alunos com as menores notas
        System.out.println("Aluno que tirou a menor nota:");
        for (Student st : students){
            if(min == st.getTestOne()){
                System.out.println(st.getCode() +" - "+st.getName()+" : Nota = "+ st.getTestOne());
            }
            if(min == st.getTestTwo()){
                System.out.println(st.getCode() +" - "+st.getName()+" : Nota = "+ st.getTestTwo());
            }
            if(min == st.getTestThree()){
                System.out.println(st.getCode() +" - "+st.getName()+" : Nota = "+ st.getTestThree());
            }
        }

        System.out.println("\n");
    }

    public static void topTreeGradesOfTheStudents(List<Student> students){
        float max = 0;
        float sMax = 0;
        float tMax = 0;
        List<Float> grades = new ArrayList<>();

        //colocar todas as notas dos alunos numa lista
        for (Student st : students){
            grades.add(st.getTestOne());
            grades.add(st.getTestTwo());
            grades.add(st.getTestThree());
        }

        //econtrar maior nota
        max = grades.get(0);
        for (Float g : grades){
            if(g > max){
                max = g;
            }
        }

        //econtrar segunda maior nota
        sMax = grades.get(0);
        for (Float g : grades){
            if(g > sMax && g != max){
                sMax = g;
            }
        }

        //econtrar terceira maior nota
        tMax = grades.get(0);
        for (Float g : grades){
            if(g > tMax && g != max && g != sMax){
                tMax = g;
            }
        }

        System.out.println("Top 3 maiores notas de alunos:");
        //Imprimir os alunos da 1º posição
        int  ft = 0;
        for (Student st : students){
            if(ft == 0){
                System.out.println("1º");
                ft++;
            }
            if(st.getTestOne() == max){
                System.out.print(" - "+st.getName()+" : Nota = "+st.getTestOne()+";");
            }
            if(st.getTestTwo() == max){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestTwo()+";");
            }
            if(st.getTestThree() == max){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestThree()+";");
            }
        }
        System.out.print("\n");


        //Imprimir os alunos da 2º posição
        ft = 0;
        for (Student st : students){
            if(ft == 0){
                System.out.println("\n"+"2º");
                ft++;
            }
            if(st.getTestOne() == sMax){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestOne()+";");
            }
            if(st.getTestTwo() == sMax){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestTwo()+";");
            }
            if(st.getTestThree() == sMax){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestThree()+";");
            }
        }
        System.out.print("\n");


        //Imprimir os alunos da 3º posição
        ft = 0;
        for (Student st : students){
            if(ft == 0){
                System.out.println("3º");
                ft++;
            }
            if(st.getTestOne() == tMax){
                System.out.print(" - "+st.getName()+" : Nota = "+st.getTestOne()+";");
            }
            if(st.getTestTwo() == tMax){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestTwo()+";");
            }
            if(st.getTestThree() == tMax){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestThree()+";");
            }
        }

        System.out.print("\n");

    }

    public static void threeLowestGrades(List<Student> students){
        float min = 0;
        float sMin = 0;
        float tMin = 0;
        List<Float> grades = new ArrayList<>();

        //colocar todas as notas dos alunos numa lista
        for (Student st : students){
            grades.add(st.getTestOne());
            grades.add(st.getTestTwo());
            grades.add(st.getTestThree());
        }

        //econtrar menor nota
        min = grades.get(0);
        for (Float g : grades){
            if(g < min){
                min = g;
            }
        }

        //econtrar segunda menor nota
        sMin = grades.get(0);
        for (Float g : grades){
            if(g < sMin && g != min){
                sMin = g;
            }
        }

        //econtrar terceira menor nota
        tMin = grades.get(0);
        for (Float g : grades){
            if(g < tMin && g != min && g != sMin){
                tMin = g;
            }
        }

        System.out.println("Top 3 menores notas de alunos:");
        //Imprimir os alunos da 1º posição
        int  ft = 0;
        for (Student st : students){
            if(ft == 0){
                System.out.println("1º");
                ft++;
            }
            if(st.getTestOne() == min){
                System.out.print(" - "+st.getName()+" : Nota = "+st.getTestOne()+";");
            }
            if(st.getTestTwo() == min){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestTwo()+";");
            }
            if(st.getTestThree() == min){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestThree()+";");
            }
        }

        //Imprimir os alunos da 2º posição
        ft = 0;
        for (Student st : students){
            if(ft == 0){
                System.out.println("\n"+"2º");
                ft++;
            }
            if(st.getTestOne() == sMin){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestOne()+";");
            }
            if(st.getTestTwo() == sMin){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestTwo()+";");
            }
            if(st.getTestThree() == sMin){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestThree()+";");
            }
        }
        System.out.print("\n");


        //Imprimir os alunos da 3º posição
        ft = 0;
        for (Student st : students){
            if(ft == 0){
                System.out.println("3º");
                ft++;
            }
            if(st.getTestOne() == tMin){
                System.out.print(" - "+st.getName()+" : Nota = "+st.getTestOne()+";");
            }
            if(st.getTestTwo() == tMin){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestTwo()+";");
            }
            if(st.getTestThree() == tMin){
                System.out.println(" - "+st.getName()+" : Nota = "+st.getTestThree()+";");
            }
        }
    }

    public static void averageOfAllStudents(List<Student> students){
        List<Float> averages = new ArrayList<Float>();
        float average = 0;

        //calcular a média de cada aluno e colocar numa lista
        for(Student st : students){
            average = (st.getTestOne() + st.getTestTwo() + st.getTestThree()) / 3;
            averages.add(average);
        }

        //ordenar a lista de médias
        averages.sort(null);//passando o parêmetro null a lista é ordenada em ordem crescente

        //imprimir a média dos alunos em ordem crescente
        System.out.println("\n"+"Média de todos os alunos da maior para a menor nota:");
        average = 0;
        int position = 1;
        for (Float ave : averages){
            for (Student st : students){
                average = (st.getTestOne() + st.getTestTwo() + st.getTestThree()) / 3;
                if(ave == average){
                    System.out.println(position+"º"+" - "+st.getCode()+" - "+st.getName()+" : Média = "+average);
                }
            }
            position++;
        }









    }
}

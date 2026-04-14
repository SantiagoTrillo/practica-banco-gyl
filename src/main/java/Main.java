import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del banco:");
        String nombre = teclado.nextLine();
        Banco banco = Banco.getInstancia(nombre);

        probarBanco(banco);
    }

    private static void probarBanco(Banco bancoPrueba) {

    }
}

//terminales remotas, un solo banco, consignaciones, agregar menú de interacción + scanner
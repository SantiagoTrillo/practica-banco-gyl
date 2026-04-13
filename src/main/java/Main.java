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
        Cuenta cuenta1 = new Cuenta("Pepito", "Avenida Roble 3885", TipoCuenta.CUENTA_CORRIENTE);
        Cuenta cuenta2 = new Cuenta("Martina", "Avenida Jabón 981", TipoCuenta.CAJA_AHORRO);
        bancoPrueba.agregarCuenta(cuenta1);
        bancoPrueba.agregarCuenta(cuenta2);

        bancoPrueba.mostrarCuenta(cuenta1);
        bancoPrueba.mostrarCuenta(cuenta2);
        bancoPrueba.agregarSaldo(cuenta1, 1000);
        bancoPrueba.mostrarCuenta(cuenta1);
        bancoPrueba.procesarTransferencia(cuenta1, cuenta2, 1000);
        bancoPrueba.mostrarCuentas();
        bancoPrueba.agregarSaldo(cuenta1, 2000);
        bancoPrueba.mostrarCuenta(cuenta1);
        bancoPrueba.procesarTransferencia(cuenta1, cuenta2, 2000);
        bancoPrueba.mostrarCuentas();
        bancoPrueba.procesarTransferencia(cuenta1, cuenta2, 1000);
        bancoPrueba.procesarTransferencia(cuenta1, cuenta2, -200);
        bancoPrueba.procesarTransferencia(cuenta1, cuenta2, 0);
    }
}

//terminales remotas para retirar y depositar dinero, un solo banco, sucursales como atributos de cuentas, retiros y consignaciones, agregar menú de interacción + scanner, excepciones, reemplazar arreglo por arraylist
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Banco banco = Banco.getInstancia();

        probarBanco(banco);
        mostrarMenu();
    }

    private static void probarBanco(Banco bancoPrueba) {
        bancoPrueba.crearSucursal("Parque Patricios");
        bancoPrueba.crearSucursal("Boedo");
    }

    public static void mostrarMenu() {
        Scanner teclado = new Scanner(System.in);

        System.out.println("Bienvenido a la sucursal " + getNombre());

        while (true) {
            int opcion;
            System.out.println("Ingrese el número que corresponda con la acción que desee realizar\n1) Registrar cuenta\n2) Depositar dinero\n3) Retirar dinero\n4) Realizar una transferencia\n5) Eliminar cuenta\n6) Salir");
            opcion = teclado.nextInt();

            if (opcion == 1) {
                TipoCuenta tipoCuenta = null;
                System.out.println("Ingrese su nombre");
                String nombre = teclado.nextLine();
                System.out.println("Ingrese su email");
                String email = teclado.nextLine();
                System.out.println("Indique el tipo de cuenta que le gustaría crear:\n1) Caja de ahorro\n2) Cuenta corriente");
                int opcionCuenta = teclado.nextInt();
                if (opcionCuenta == 1) {
                    tipoCuenta = TipoCuenta.CAJA_AHORRO;
                } else if (opcionCuenta == 2) {
                    tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
                } else {
                    System.out.println("Opción inválida");
                }
                registrarCuenta(nombre, email, tipoCuenta);
            } else if (opcion == 2) {
                System.out.println("Ingrese el email de la cuenta a la que desea hacer un depósito:");
                String emailBuscado = teclado.nextLine();
                Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
                System.out.println("Ingrese el monto que desea depositar:");
                double monto = teclado.nextFloat();
                depositar(cuentaBuscada, monto);
            } else if (opcion == 3) {
                System.out.println("Ingrese el email de la cuenta a la que desea hacer un retiro:");
                String emailBuscado = teclado.nextLine();
                Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
                System.out.println("Ingrese el monto que desea retirar:");
                double monto = teclado.nextFloat();
                retirar(cuentaBuscada, monto);
            } else if (opcion == 4) {
                System.out.println("Ingrese el email de la cuenta desde la que quiere enviar la transferencia:");
                String emailTransferente = teclado.nextLine();
                Cuenta cuentaTransferente = buscarCuenta(emailTransferente);
                System.out.println("Ingrese el email de la cuenta a la que quiere enviar la transferencia:");
                String emailTransferido = teclado.nextLine();
                Cuenta cuentaTransferido = buscarCuenta(emailTransferido);
                System.out.println("Ingrese el monto que desea transferir:");
                double monto = teclado.nextFloat();
                transferir(cuentaTransferente, cuentaTransferido, monto);
            } else if (opcion == 5) {
                System.out.println("Ingrese el email de la cuenta que desea eliminar:");
                String emailBuscado = teclado.nextLine();
                Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
                System.out.println("¿Está seguro que desea eliminar la cuenta? (S o N)");
                String eleccion = teclado.nextLine().toUpperCase();
                if (eleccion.equals("S")) {
                    eliminarCuenta(cuentaBuscada);
                } else if (!eleccion.equals("N")) {
                    System.out.println("Opción inválida");
                }
            } else if (opcion == 6) {
                System.out.println("Gracias por utilizar nuestros servicios");
                break;
            } else {
                System.out.println("Opción inválida");
            }
        }
    }
}

//terminales remotas, un solo banco, consignaciones, agregar menú de interacción + scanner
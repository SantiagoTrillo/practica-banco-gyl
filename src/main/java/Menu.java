import java.util.Scanner;

public class Menu {
    private final Banco banco;
    private final Scanner teclado;

    public Menu(Banco banco) {
        this.banco = banco;
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean correr = true;
        System.out.println("-----Bienvenido a nuestro banco-----");
        System.out.println("¿A qué sucursal le gustaría acceder? Escriba el nombre que corresponda");
        banco.mostrarSucursales();
        String sucursalElegida = teclado.nextLine();

        while (correr) {
            System.out.println("""
                    Ingrese el número que corresponda con la acción que desee realizar:
                    1) Registrar cuenta
                    2) Iniciar sesión
                    3) Depositar dinero
                    4) Retirar dinero
                    5) Realizar una transferencia
                    6) Eliminar cuenta
                    7) Salir""");

            int opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    registrarCuenta(sucursalElegida);
                    break;
                case 2:
                    iniciarSesion();
            }
        }
    }

    private void registrarCuenta(String nombreSucursal) {
        Sucursal sucursal = banco.buscarSucursal(nombreSucursal);
        TipoCuenta tipoCuenta = null;
        System.out.println("Ingrese su nombre");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese su email");
        String email = teclado.nextLine();
        System.out.println("Ingrese su pin");
        int pin = teclado.nextInt();
        System.out.println("""
                Indique el tipo de cuenta que le gustaría crear:
                1) Caja de ahorro
                2) Cuenta corriente""");
        int opcionCuenta = teclado.nextInt();
        if (opcionCuenta == 1) {
            tipoCuenta = TipoCuenta.CAJA_AHORRO;
        } else if (opcionCuenta == 2) {
            tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
        } else {
            System.out.println("Opción inválida");
        }
        sucursal.registrarCuentaSucursal(nombre, email, pin, false, tipoCuenta);
    }

    private void iniciarSesion() {

    }

//    System.out.println("Ingrese el email de la cuenta a la que desea hacer un depósito:");
//    String emailBuscado = teclado.nextLine();
//    Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
//    System.out.println("Ingrese el monto que desea depositar:");
//    double monto = teclado.nextFloat();
//    depositar(cuentaBuscada, monto);
//
//    System.out.println("Ingrese el email de la cuenta a la que desea hacer un retiro:");
//    String emailBuscado = teclado.nextLine();
//    Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
//    System.out.println("Ingrese el monto que desea retirar:");
//    double monto = teclado.nextFloat();
//    retirar(cuentaBuscada, monto);
//
//    System.out.println("Ingrese el email de la cuenta desde la que quiere enviar la transferencia:");
//    String emailTransferente = teclado.nextLine();
//    Cuenta cuentaTransferente = buscarCuenta(emailTransferente);
//    System.out.println("Ingrese el email de la cuenta a la que quiere enviar la transferencia:");
//    String emailTransferido = teclado.nextLine();
//    Cuenta cuentaTransferido = buscarCuenta(emailTransferido);
//    System.out.println("Ingrese el monto que desea transferir:");
//    double monto = teclado.nextFloat();
//    transferir(cuentaTransferente, cuentaTransferido, monto);
//
//    System.out.println("Ingrese el email de la cuenta que desea eliminar:");
//    String emailBuscado = teclado.nextLine();
//    Cuenta cuentaBuscada = buscarCuenta(emailBuscado);
//    System.out.println("¿Está seguro que desea eliminar la cuenta? (S o N)");
//    String eleccion = teclado.nextLine().toUpperCase();
//    if (eleccion.equals("S")) {eliminarCuenta(cuentaBuscada);
//    } else if (!eleccion.equals("N")) {System.out.println("Opción inválida");
//    }
}
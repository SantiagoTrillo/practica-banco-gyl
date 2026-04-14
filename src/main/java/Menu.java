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
        while (correr) {
            boolean sucursalIniciada = true;
            boolean sesionIniciada = false;
            System.out.println("-----Bienvenido a nuestro banco-----");
            System.out.println("¿A qué sucursal le gustaría acceder? Escriba el nombre que corresponda (Puede escribir S para salir del programa)");
            banco.mostrarSucursales();
            String nombreSucursal = teclado.nextLine();
            if (nombreSucursal.equalsIgnoreCase("S")) {
                correr = false;
                System.out.println("\nGracias por visitar nuestro banco\n");
            } else {
                Sucursal sucursalElegida = buscarSucursal(nombreSucursal);

                while (sucursalIniciada) {
                    System.out.println("""
                    Ingrese el número que corresponda con la acción que desee realizar:
                    1) Registrar cuenta
                    2) Iniciar sesión
                    3) Depositar dinero
                    4) Retirar dinero
                    5) Realizar una transferencia
                    6) Eliminar cuenta
                    7) Cerrar sesión
                    8) Salir de la sucursal
                    """);

                    int opcion = teclado.nextInt();
                    teclado.nextLine();

                    switch (opcion) {
                        case 1:
                            sesionIniciada = registrarCuenta(sucursalElegida);
                            break;
                        case 2:
                            if (!sesionIniciada) {
                                sesionIniciada = iniciarSesion(sucursalElegida);
                            } else {
                                System.out.println("\nYa hay una sesión activa\n");
                            }
                            break;
                        case 3:
                        case 7:
                            if (sesionIniciada) {
                                sesionIniciada = false;
                                System.out.println("Sesión cerrada con éxito");
                            } else {
                                System.out.println("\nNo hay una sesión activa\n");
                            }
                            break;
                        case 8:
                            sucursalIniciada = false;
                    }
                }
            }
        }
    }

    private boolean registrarCuenta(Sucursal sucursalElegida) {
        TipoCuenta tipoCuenta = null;
        boolean sesionIniciada = false;

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

        Cuenta cuentaRegistrada = sucursalElegida.crearCuenta(nombre, email, pin, false, tipoCuenta);
        System.out.println("\nCuenta creada con éxito. Bienvenido, " + cuentaRegistrada.getNombre() + "\n");
        sesionIniciada = true;
        return sesionIniciada;
    }

    private boolean iniciarSesion(Sucursal sucursalElegida) {
        boolean sesionIniciada = false;

        System.out.println("Ingrese su email");
        String email = teclado.nextLine();
        System.out.println("Ingrese su pin");
        int pin = teclado.nextInt();
        Cuenta cuenta = sucursalElegida.buscarCuenta(email);
        if (cuenta != null) {
            if (cuenta.getPin() == pin) {
                sesionIniciada = true;
                System.out.println("Sesión iniciada con éxito");
            } else {
                System.out.println("Contraseña incorrecta");
            }
        } else {
            System.out.println("No se ha encontrado una cuenta asociada con el email proporcionado");
        }

        return sesionIniciada;
    }

    private Sucursal buscarSucursal(String nombre) {
        return banco.buscarSucursalBanco(nombre);
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
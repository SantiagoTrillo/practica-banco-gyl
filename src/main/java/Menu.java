import java.util.Scanner;

public class Menu {
    private final Banco banco;
    private Sucursal sucursal;
    private Cuenta sesionActiva;
    private final Scanner teclado;

    public Menu(Banco banco) {
        this.banco = banco;
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {
        boolean correr = true;
        while (correr) {
            System.out.println("\n-----Bienvenido a nuestro banco-----\n");
            System.out.println("\n¿A qué sucursal le gustaría acceder? Escriba el nombre que corresponda (Puede escribir S para salir del programa)\n");
            banco.mostrarSucursales();
            String nombreSucursal = teclado.nextLine();
            if (nombreSucursal.equalsIgnoreCase("S")) {
                correr = false;
                System.out.println("\nGracias por visitar nuestro banco\n");
            } else {
                sucursal = banco.buscarSucursal(nombreSucursal);

                if (sucursal == null) {
                    System.out.println("\nOpción inválida\n");
                }

                while (sucursal != null) {
                    System.out.println("""
                            Ingrese el número que corresponda con la acción que desee realizar:
                            1) Registrar cuenta
                            2) Iniciar sesión
                            3) Depositar dinero
                            4) Retirar dinero
                            5) Realizar una transferencia
                            6) Mostrar datos de la cuenta
                            7) Eliminar cuenta
                            8) Cerrar sesión
                            9) Salir de la sucursal
                            """);
                    if (sesionActiva != null && sesionActiva.isAdmin()) {
                        System.out.println("""
                                Acciones de administrador:
                                10) Mostrar datos de esta sucursal
                                11) Mostrar datos de otra sucursal
                                12) Mostrar datos del banco
                                13) Crear sucursal
                                14) Eliminar una cuenta
                                """);
                    }

                    int opcion = teclado.nextInt();
                    teclado.nextLine();

                    switch (opcion) {
                        case 1:
                            registrarCuenta();
                            break;
                        case 2:
                            if (sesionActiva == null) {
                                iniciarSesion();
                            } else {
                                System.out.println("\nYa hay una sesión activa\n");
                            }
                            break;
                        case 3:
                            if (sesionActiva != null) {
                                procesarDeposito();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 4:
                            if (sesionActiva != null) {
                                procesarRetiro();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 5:
                            if (sesionActiva != null) {
                                procesarTransferencia();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 6:
                            if (sesionActiva != null) {
                                System.out.println(sesionActiva);;
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 7:
                            if (sesionActiva != null) {
                                procesarEliminacion();
                            } else {
                                System.out.println("\nNecesita iniciar sesión para realizar esta acción\n");
                            }
                            break;
                        case 8:
                            if (sesionActiva != null) {
                                sesionActiva = null;
                                System.out.println("\nSesión cerrada con éxito\n");
                            } else {
                                System.out.println("\nNo hay una sesión activa\n");
                            }
                            break;
                        case 9:
                            sucursal = null;
                            sesionActiva = null;
                            break;
                        case 10:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                sucursal.mostrarCuentas();
                            }
                            break;
                        case 11:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                System.out.println("\nIngrese el nombre de la sucursal\n");
                                banco.mostrarSucursales();
                                String sucursalBuscada = teclado.nextLine();
                                Sucursal otraSucursal = banco.buscarSucursal(sucursalBuscada);
                                otraSucursal.mostrarCuentas();
                            }
                            break;
                        case 12:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                banco.mostrarCuentas();
                            }
                            break;
                        case 13:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                System.out.println("Ingrese el nombre de la nueva sucursal");
                                String nombreNuevaSucursal = teclado.nextLine();
                                banco.crearSucursal(nombreNuevaSucursal);
                            }
                            break;
                        case 14:
                            if (sesionActiva == null || !sesionActiva.isAdmin()) {
                                System.out.println("\nOpción inválida\n");
                                break;
                            } else {
                                procesarEliminacionAdmin();
                            }
                            break;
                        default:
                            System.out.println("\nOpción inválida\n");
                    }
                }
            }
        }
    }

    private void registrarCuenta() {
        TipoCuenta tipoCuenta = null;

        System.out.println("\nIngrese su nombre\n");
        String nombre = teclado.nextLine();
        System.out.println("\nIngrese su email\n");
        String email = teclado.nextLine();
        System.out.println("\nIngrese su pin\n");
        int pin = teclado.nextInt();
        System.out.println("""
                Indique el tipo de cuenta que le gustaría crear:
                1) Caja de ahorro
                2) Cuenta corriente""");
        int opcionCuenta = teclado.nextInt();
        switch (opcionCuenta) {
            case 1:
                tipoCuenta = TipoCuenta.CAJA_AHORRO;
                break;
            case 2:
                tipoCuenta = TipoCuenta.CUENTA_CORRIENTE;
                break;
            default:
                System.out.println("\nOpción inválida\n");
        }

        Cuenta cuentaRegistrada = sucursal.crearCuenta(nombre, email, pin, false, tipoCuenta);
        sesionActiva = cuentaRegistrada;
        System.out.println("\nCuenta creada con éxito. Bienvenido, " + cuentaRegistrada.getNombre() + "\n");
    }

    private void iniciarSesion() {
        System.out.println("\nIngrese su email\n");
        String email = teclado.nextLine();
        System.out.println("\nIngrese su pin\n");
        int pin = teclado.nextInt();
        Cuenta cuenta = sucursal.buscarCuenta(email);
        if (cuenta != null) {
            if (cuenta.getPin() == pin) {
                this.sesionActiva = cuenta;
                System.out.println("\nSesión iniciada con éxito. Bienvenido, " + cuenta.getNombre() + "\n");
            } else {
                System.out.println("\nContraseña incorrecta\n");
            }
        } else {
            System.out.println("\nNo se ha encontrado una cuenta asociada con el email ingresado\n");
        }
    }

    private void procesarDeposito() {
        System.out.println("\nIngrese el monto que desea depositar en su cuenta\n");
        double monto = teclado.nextInt();
        sucursal.depositar(sesionActiva, monto);
        System.out.println("\nHa depositado $" + monto + " en su cuenta\n");
    }

    private void procesarRetiro() {
        System.out.println("\nIngrese el monto que desea retirar de su cuenta\n");
        double monto = teclado.nextInt();
        sucursal.retirar(sesionActiva, monto);
        System.out.println("\nHa retirado $" + monto + " de su cuenta\n");
    }

    private void procesarTransferencia() {
        System.out.println("\nIngrese el monto que desea transferir desde su cuenta\n");
        double monto = teclado.nextInt();
        System.out.println("\nIngrese el email de la persona a la que transferirá el dinero\n");
        String emailTransferido = teclado.nextLine();
        Cuenta cuentaTransferido = banco.buscarCuenta(emailTransferido);

        if (cuentaTransferido != null && !cuentaTransferido.getEmail().equals(sesionActiva.getEmail())) {
            sucursal.transferir(sesionActiva, cuentaTransferido, monto);
            System.out.println("\nTransferencia realizada con éxito\n");
        } else {
            System.out.println("\nNo se ha encontrado una cuenta asociada con el email ingresado\n");
        }
    }

    private void procesarEliminacion() {
        System.out.println("\n¿Está seguro que desea eliminar su cuenta? (Presione Enter para continuar)\n");
        String eliminar = teclado.nextLine();
        if (eliminar.isBlank()) {
            sucursal.eliminarCuenta(sesionActiva);
            sesionActiva = null;
            System.out.println("\nCuenta eliminada con éxito\n");
        } else {
            System.out.println("\nVolviendo al menú de acciones\n");
        }
    }

    private void procesarEliminacionAdmin() {
        System.out.println("\nIngrese el mail de la cuenta que desea eliminar\n");
        String emailCuentaEliminar = teclado.nextLine();
        Cuenta cuentaEliminar = banco.buscarCuenta(emailCuentaEliminar);
        if (cuentaEliminar != null) {
            System.out.println("\nLos datos de la cuenta seleccionada son los siguientes:\n");
            System.out.println(cuentaEliminar);
            System.out.println("¿Está seguro que desea eliminar esta cuenta? (Presione Enter para continuar)");
            String eliminar = teclado.nextLine();
            if (eliminar.isBlank()) {
                sucursal.eliminarCuenta(cuentaEliminar);
                System.out.println("\nCuenta eliminada con éxito\n");
            } else {
                System.out.println("\nVolviendo al menú de acciones\n");
            }
        } else {
            System.out.println("\nNo se ha encontrado una cuenta asociada con el email ingresado\n");
        }
    }
}
public class Main {
    static void main(String[] args) {
        Banco banco = Banco.getInstancia();
        Menu menu = new Menu(banco);

        menu.mostrarMenu();
    }
}
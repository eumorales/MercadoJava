package thread;

class MinhaThread extends Thread {
    
    private String mensagem;
    private int intervalo;

    public MinhaThread(String mensagem, int intervalo) {
        this.mensagem = mensagem;
        this.intervalo = intervalo;
    }

    public void run() {
        try {
            while (true) {
                System.out.println(mensagem);
                Thread.sleep(intervalo);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrompida: " + mensagem);
        }
    }
}

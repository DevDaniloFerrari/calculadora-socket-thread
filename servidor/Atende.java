package servidor;

import java.net.Socket;

import util.Comunicacao;
import util.Requisicao;
import util.Resposta;

public class Atende extends Thread {
    public Socket cliente;

    public Atende(Socket cliente) {
        this.cliente = cliente;
    }

    @Override
    public void run() {
        Comunicacao comunicacao = new Comunicacao(cliente);

        Requisicao requisicao = (Requisicao) comunicacao.receive();
        Resposta resposta = new Resposta();

        char operacao = requisicao.getOperacao();

        switch (operacao) {
            case '+':
                resposta.setResultado(requisicao.getNumero1() + requisicao.getNumero2());
                resposta.setStatus((Resposta.OK));
                break;
            case '-':
                resposta.setResultado(requisicao.getNumero1() - requisicao.getNumero2());
                resposta.setStatus((Resposta.OK));
                break;
            case '*':
                resposta.setResultado(requisicao.getNumero1() * requisicao.getNumero2());
                resposta.setStatus((Resposta.OK));
                break;
            case '/':
                if (requisicao.getNumero2() != 0) {
                    resposta.setResultado(requisicao.getNumero1() / requisicao.getNumero2());
                    resposta.setStatus((Resposta.OK));
                    break;
                } else {
                    resposta.setStatus(Resposta.DIVISAO_POR_ZERO);
                }
                break;
            default:
                resposta.setStatus((Resposta.OPERADOR_INVALIDO));
                break;
        }

        comunicacao.send(resposta);
        System.out.println("Operação realiada: " + requisicao.getOperacao());

        try {
            cliente.close();
            System.out.println("Conexão encerrada.");
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexão.");
        }
    }
}

import javax.swing.JOptionPane;
import java.text.Normalizer;

public class SepararNomeSobrenome {
    public static void main(String[] args) {
        // Solicita ao usuário que insira o nome completo usando um JOptionPane
        String nomeCompleto = null;
        boolean entradaValida = false;

        // Loop até que o usuário insira uma entrada válida sem números
        while (!entradaValida) {
            nomeCompleto = JOptionPane.showInputDialog("Insira o nome completo do aluno:");

            // Verifica se o usuário pressionou Cancelar ou fechou a janela do JOptionPane
            if (nomeCompleto == null || nomeCompleto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum nome foi inserido. O programa será encerrado.");
                System.exit(0);
            }

            // Verifica se a entrada contém números
            if (!contemNumeros(nomeCompleto)) {
                entradaValida = true;
            } else {
                JOptionPane.showMessageDialog(null, "A entrada não deve conter números. Por favor, tente novamente.");
            }
        }

        // Encontra a posição do primeiro espaço em branco
        int primeiroEspaco = nomeCompleto.indexOf(' ');

        // Extrai o nome (de 0 até o primeiro espaço em branco)
        String nome = nomeCompleto.substring(0, primeiroEspaco);

        // Extrai o sobrenome (a partir do primeiro espaço em branco + 1)
        String sobrenome = nomeCompleto.substring(primeiroEspaco + 1);

        // Gera o email educacional
        String emailEducacional = gerarEmailEducacional(nome, sobrenome);

        // Mostra o resultado em um JOptionPane
        JOptionPane.showMessageDialog(null, "Nome: " + nome + "\nSobrenome: " + sobrenome + "\nEmail Educacional: " + emailEducacional.toLowerCase());
    }

    public static String gerarEmailEducacional(String nome, String sobrenome) {
        // Primeira letra do primeiro sobrenome
        char primeiraLetraSobrenome1 = sobrenome.charAt(0);

        // Encontra a posição do último espaço em branco (se houver mais de um sobrenome)
        int ultimoEspaco = sobrenome.lastIndexOf(' ');

        // Se houver mais de um espaço em branco, considere o último sobrenome como "último nome"
        String ultimoNome = ultimoEspaco != -1 ? sobrenome.substring(ultimoEspaco + 1) : sobrenome;

        // Remove acentuações e caracteres especiais do email educacional e converte para minúsculas
        String emailEducacional = nome + "." + (ultimoEspaco != -1 ? primeiraLetraSobrenome1 : "") + ultimoNome;
        emailEducacional = Normalizer.normalize(emailEducacional, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        emailEducacional = emailEducacional.toLowerCase() + "@uf.escola.br";

        return emailEducacional;
    }

    public static boolean contemNumeros(String texto) {
        for (char c : texto.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}

package fatecpg.Atividade.StreamLambda.View;

import fatecpg.Atividade.StreamLambda.Service.ConsomeAPI;

import java.util.List;
import java.util.Scanner;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

        try {
            System.out.println("Informe o preço dos produtos que você deseja");
            float precoMax = scan.nextFloat();

            String respostaAPI = ConsomeAPI.requisicao();

            Pattern padrao = Pattern.compile("\"title\": \"(.*?)\",.*?\"price\": (\\d+\\.?\\d*)");
            Matcher matcher = padrao.matcher(respostaAPI);

            System.out.println("imperdiveis");


            List<String> produtosPromocionais = matcher.results()
                    .map(result -> {
                        String nomeProduto = result.group(1).toUpperCase();
                        float precoProduto = Float.parseFloat(result.group(2));
                        return precoProduto <= precoMax ? "PROMOÇÃO: " + nomeProduto + " - Preço: " + precoProduto : null;
                    })
                    .filter(item -> item != null)
                    .collect(Collectors.toList());


            if (produtosPromocionais.isEmpty()) {
                System.out.println("Não há produtos com preço menor ou igual a " + precoMax);
            } else {
                produtosPromocionais.forEach(System.out::println);
            }

//            List<Float> precos = matcher.results()
//                    .map(result -> Float.parseFloat(result.group(1)))
//                    .collect(Collectors.toList());
//
//
//
//            precos.forEach(preco -> {
//                if (preco <= precoMax) {
//                    System.out.println("PROMOÇÃO: Produto com preço " + preco);
//                }
//            });

            // precos.forEach(preco -> System.out.println("PROMOÇÂO " + preco));
            // toUpperCase()

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

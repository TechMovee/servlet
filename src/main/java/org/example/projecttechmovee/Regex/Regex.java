package org.example.projecttechmovee.Regex;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.*;

public class Regex {

//      Verifica se o nome é válido (Se o nome contém apenas letras).
        public boolean verificarNome(String nome){
            String regex = "^[A-Za-zÀ-ÖØ-öø-ÿ]+( *[A-Za-zÀ-ÖØ-öø-ÿ]*)+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(nome);

            return matcher.matches();
        }

//      Verifica se o Email é válido (Se o Email possui o domínio correto).
        public boolean verificarEmail(String email){
            String regex = "^[a-zA-Z0-9]([._-]?[a-zA-Z0-9]+)*@[a-zA-Z]+(\\.(com|org|net)(\\.br)?)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
        }

        public boolean verificarFoto(String foto){
            String regex = ".*\\.(png|jpg|jpeg|bmp|svg)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(foto);

            return matcher.matches();
        }


//      Verifica se a senha é válida (Se a senha possui letra maiúsculas, minúsculas, número e caractere especial).
        public boolean verificarPassword(String password){
            String regex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])(?=.*([!-/]|[:-@]|[\\[-`]|[{-~])).+$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(password);

            return matcher.matches();
        }

//      Verifica se o Telefone é válido (No formato padrão brasileiro)
        public boolean verificarTelefone(String telefone){
            String regex = "^\\(?[1-9]{2}\\)?\\s?9[0-9]{4}-?[0-9]{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(telefone);

            return matcher.matches();
        }

//      Verifica se o CEP é válido (Deve ter 8 dígitos).
        public boolean verificarCEP(String cep){
            return cep.replaceAll("\\D ", "").length() == 8;
        }

//      Verifica se a CNH é válida (Deve ter 9 dígitos).
        public boolean verificarCNH(String cnh){
            return cnh.replaceAll("\\D", "").length() == 9;
        }

//      Verifica se o CPF é válido (Usando os cálculos de validação)
        public boolean verificarCPF(String cpf) {
            cpf = cpf.replaceAll("\\D", "");
            return cpf.length()==11;
        }

//      Verifica se a placa é válida (Nos formato antigo e Mercosul).
        public boolean verificarPlaca(String placa){
            placa = placa.replaceAll("[^a-zA-Z0-9]", "");
            String regex = "^(?:[a-zA-Z]{3}[0-9]{4}|[a-zA-Z]{3}[0-9][a-zA-Z][0-9]{2})$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(placa);

            return matcher.matches();
        }

        // Verifica se a Data é válida (Nos formatos com barra, espaço e travessão
        public boolean verificarData(String data){
            String regex = "^([0-9]{4})[\\/\\-.](0[1-9]|1[0-2])[\\/\\-.](0[1-9]|[12][0-9]|3[01])$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(data);

            if (!matcher.matches()) {
                return false;
            }

            // Converte a string para LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String dataFormatada = data.replaceAll("[\\-.\\s]", "/");
            LocalDate dataNascimentoLocalDate = LocalDate.parse(dataFormatada, formatter);
            LocalDate dataAtual = LocalDate.now();

            // Calcula a diferença em anos entre a data de nascimento e a data atual
            long idade = ChronoUnit.YEARS.between(dataNascimentoLocalDate, dataAtual);

            // Verifica se a idade é maior ou igual a 18 anos
            return idade >= 18;
        }
}

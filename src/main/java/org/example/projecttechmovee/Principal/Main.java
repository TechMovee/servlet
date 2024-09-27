package org.example.projecttechmovee.Principal;
//
//import org.example.projecttechmovee.ClasseTabelas.*;
//import org.example.projecttechmovee.ClasseTabelasDAO.*;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//import static java.lang.Thread.sleep;
//
public class Main {}
//    public static void main(String[] args) {
//        Conexao conexao = new Conexao();
//
//        Scanner input = new Scanner(System.in);
//
//        String red = "\u001B[91m";
//        String green = "\u001B[92m";
//        String reset = "\u001B[0m";
//        int opcao1 = 0, opcao2 = 0, opcao3 = 0;
//        int funcao = 0;
//
//
//        do {
//            System.out.println(menuIncio());
//            System.out.print("Escolha uma opção: ");
//
//            try {
//                opcao1 = input.nextInt();
//            } catch (InputMismatchException erro1){
//                System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();
//            }  //Caso seja digitado outras coisas;
//
//            switch (opcao1){
//                case 1 ->{
//                    System.out.println(load());
//                    time(2);
//                    if (conexao.conectar()) {System.out.println(green + "Conexão bem-sucedida!" + reset);}
//                    else {System.out.println(red + "Falha ao conectar!" + reset);}
//                }   //Testar conexão
//                case 2 ->{
//                    System.out.println(load());
//                    time(2);
//                    if (conexao.desconectar()) {System.out.println(green +"Desconexão realiada!" + reset);}
//                    else {System.out.println(red + "Falha ao desconectar!" + green);}
//                }   //Testar desconexão
//                case 3 ->{
//
//                    do {
//                        System.out.println(load());
//                        time(2);
//                        System.out.println(menuComuns());
//                        System.out.print("Escolha uma opção: ");
//
//                        try {
//                            opcao2 = input.nextInt();
//                        } catch (InputMismatchException erro1){
//                            System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                        switch (opcao2){
//                            //Tabela [01] - Endereços
//                            case 1  -> {
//
//                                do {
//                                    EnderecoDAO enderecoDAO = new EnderecoDAO(conexao);
//
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();} //Validar opção
//
//                                    switch (opcao2) {
//                                        case 1 -> {
//                                            System.out.println(load());
//                                            time(2);
//
//                                            System.out.print("Digite o ID do endereço: ");
//                                            int id = input.nextInt();
//                                            try {
//                                                Endereco endereco = enderecoDAO.buscarEndereco(id);
//                                                if (endereco != null) {
//                                                    System.out.println("Endereço encontrado: " + endereco);
//                                                } else {
//                                                    System.out.println("Endereço não encontrado.");
//                                                }
//                                            } catch (Exception e) {
//                                                throw new RuntimeException(e);
//                                            }
//                                        } //Crud Select
//                                        case 2 -> {
//                                            System.out.println(load());
//                                            time(2);
//
//                                            System.out.print("Digite o ID: ");
//                                            int id = input.nextInt();
//                                            System.out.print("Digite o CEP: ");
//                                            String cep = input.next();
//                                            System.out.print("Digite o Bairro: ");
//                                            String bairro = input.next();
//                                            System.out.print("Digite a Rua: ");
//                                            String rua = input.next();
//                                            System.out.print("Digite o Número: ");
//                                            String numero = input.next();
//
//                                            Endereco novoEndereco = new Endereco(id, cep, bairro, rua, numero);
//                                            try {
//                                                int resultado = enderecoDAO.adicionarEndereco(novoEndereco);
//                                                if (resultado == 1) {
//                                                    System.out.println("Endereço adicionado com sucesso!");
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Erro ao adicionar o endereço: " + e.getMessage());
//                                            }
//                                        } //Crud Insert
//                                        case 3 -> {
//                                            System.out.println(load());
//                                            time(2);
//
//                                            System.out.print("Digite o ID do endereço a ser atualizado: ");
//                                            int id = input.nextInt();
//                                            System.out.print("Digite o novo CEP: ");
//                                            String cep = input.next();
//                                            System.out.print("Digite o novo Bairro: ");
//                                            String bairro = input.next();
//                                            System.out.print("Digite a nova Rua: ");
//                                            String rua = input.next();
//                                            System.out.print("Digite o novo Número: ");
//                                            String numero = input.next();
//
//                                            Endereco enderecoAtualizado = new Endereco(id, cep, bairro, rua, numero);
//                                            try {
//                                                int resultado = enderecoDAO.atualizarEndereco(enderecoAtualizado);
//                                                if (resultado == 1) {
//                                                    System.out.println("Endereço atualizado com sucesso!");
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Erro ao atualizar o endereço: " + e.getMessage());
//                                            }
//
//                                        } //Crud Update
//                                        case 4 -> {
//                                            System.out.println(load());
//                                            time(2);
//
//                                            System.out.print("Digite o ID do endereço a ser deletado: ");
//                                            int id = input.nextInt();
//                                            try {
//                                                int resultado = enderecoDAO.deletarEndereco(id);
//                                                if (resultado == 1) {
//                                                    System.out.println("Endereço deletado com sucesso!");
//                                                }
//                                            } catch (Exception e) {
//                                                System.out.println("Erro ao deletar o endereço: " + e.getMessage());
//                                            }
//                                        } //Crud Delete
//                                        case 5 -> {
//                                            System.out.println("Manutenção");
//                                        } //Crud Livre
//                                        case 6 -> {
//                                            System.out.println("Voltando ao menu tabelas");
//                                        } //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//                            }
//
//                            //Tabela [02] - Telefones
//                            case 2  -> {
//
//                                do {
//                                    TelefoneDAO telefoneDAO = new TelefoneDAO((Conexao) conexao.getConexao());
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//
//                                        case 1 -> {
//                                            System.out.println(load());
//                                            time(2);
//
//                                            System.out.print("Digite o ID do telefone: ");
//                                            int id = input.nextInt();
//                                            try {
//                                                Telefone telefone = TelefoneDAO.buscarTelefone(id);
//                                                if (telefone != null) {
//                                                    System.out.println("Telefone encontrado: " + telefone);
//                                                } else {
//                                                    System.out.println("Telefone não encontrado.");
//                                                }
//                                            } catch (Exception e) {
//                                                throw new RuntimeException(e);
//                                            }
//
//
//                                        } //Crud Select
//                                        case 2 -> {
//                                            System.out.println(load());
//                                            time(2);
//
//                                            System.out.println("Digite o Id: ");
//                                            int id = input.nextInt();
//                                            System.out.println("Digite o Número: ");
//                                            String numero = input.next();
//
//
//
//
//                                        } //Crud Insert
//                                        case 3 -> {
//
//                                            System.out.println("Digite o Id: ");
//                                            int id = input.nextInt();
//                                            System.out.println("Digite o Número: ");
//                                            String numero = input.next();
//
//
//                                        } //Crud Update
//                                        case 4 -> {
//
//                                            System.out.println("Digite o Id: ");
//                                            int id = input.nextInt();
//
//
//
//                                        } //Crud Delete
//                                        case 5 -> {
//                                            System.out.println("Manutenção");
//                                        } //Crud Livre
//                                        case 6 -> {
//                                            System.out.println("Voltando ao menu tabelas");
//                                        } //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//                            }
//
//                            //Tabela [03] - Responsáveis
//                            case 3  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {
//
//                                            System.out.println("Digite o");
//
//
//                                        } //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {
//                                            System.out.println("Manutenção");
//                                        } //Crud Livre
//                                        case 6 -> {
//                                            System.out.println("Voltando ao menu tabelas");
//                                        } //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//
//                            }
//
//                            //Tabela [04] - Responsaveis_Telefones
//                            case 4  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//
//                            }
//
//                            //Tabela [05] - Responsaveis_Roles
//                            case 5  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//                            }
//
//                            //Tabela [06] - Transportadores
//                            case 6  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//
//                            }
//
//                            //Tabela [07] - Transportadores_Telefones
//                            case 7  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//                            }
//
//                            //Tabela [08] - Fotos
//                            case 8  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//
//                            }
//
//                            //Tabela [09] - Vans
//                            case 9  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//
//                            }
//
//                            //Tabela [10] - Alunos
//                            case 10 -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//
//                            }
//
//                            //Tabela [11] - Rotas
//                            case 11 -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao2 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao2) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao2 != 6);
//
//                            }
//
//                            //Volta ao menu principal
//                            case 12 -> {
//                                System.out.println("Voltando ao menu principal");
//                            }
//                            default -> System.out.println(red +"Opção inválida" + reset);
//                        }
//
//                    }while (opcao2 != 12);
//
//                }   //Acessar tabelas comuns
//                case 4 ->{
//
//                    do {
//                        System.out.println(load());
//                        time(2);
//                        System.out.println(menuAdmins());
//                        System.out.print("Escolha uma opção: ");
//
//                        try {
//                            opcao3 = input.nextInt();
//                        } catch (InputMismatchException erro1){
//                            System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                        switch (opcao3){
//                            case 1  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao3 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao3) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao3 != 6);
//
//                            }  //Tabela [01] - Admins
//                            case 2  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao3 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao3) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao3 != 6);
//
//                            }  //Tabela [02] - Roles
//                            case 3  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao3 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//                                    switch (opcao3) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao3 != 6);
//
//                            }  //Tabela [03] - Admins_Roles
//                            case 4  -> {
//
//                                do {
//                                    System.out.println(funcoesSQL());
//                                    System.out.print("Escolha uma opção SQL: ");
//
//                                    try {
//                                        opcao3 = input.nextInt();
//                                    } catch (InputMismatchException erro1){
//                                        System.out.println("\n\n|ERRO: " + erro1 + " |\n"); input.next();}
//
//
//                                    switch (opcao3) {
//                                        case 1 -> {;} //Crud Select
//                                        case 2 -> {;} //Crud Insert
//                                        case 3 -> {;} //Crud Update
//                                        case 4 -> {;} //Crud Delete
//                                        case 5 -> {;} //Crud Livre
//                                        case 6 -> {;} //Volta ao loop anterior
//                                        default -> System.out.println(red +"Opção inválida" + reset);
//                                    }
//
//                                }while (opcao3 != 6);
//
//
//
//                            }  //Tabela [04] - Responsaveis_Roles
//                            case 5  -> {
//                                System.out.println("Voltando ao menu principal");
//                            }  //Volta ao menu principal
//                            default -> System.out.println(red +"Opção inválida" + reset);
//
//                        }
//
//                    }while (opcao3 != 5);
//
//
//
//                }   //Acessar tabelas admins
//                case 5 ->{
//                    System.out.println("\nFoi um prazer tê-lo como tester");
//                }   //Encerrar programa
//                default -> System.out.println(red +"Opção inválida" + reset);
//
//            }
//        } while (opcao1 != 5);
//
//        System.out.println("\n|PROGRAMA ENCERRADO!|");
//    }
//
//
//    public static String menuIncio(){
//        String reset = "\u001B[0m";
//        String blue = "\u001B[34m";
//        String cyan = "\u001B[96m";
//
//        return blue + "\n\n|-=-=-=-=-=-=-=-=-=-「 ✦ MENU ✦ 」-=-=-=-=-=-=-=-=-=-|" + cyan +
//                "\n\t[01] - Testar conexão com o banco de dados" +
//                "\n\t[02] - Testar desconexão com o banco de dados" +
//                "\n\t[03] - Acessar tabelas comuns" +
//                "\n\t[04] - Acessar tabelas admins" +
//                "\n\t[05] - Sair" + blue +
//                "\n|-=-=-=-=-=-=-=-=-「 ✦ TechMovee ✦ 」=-=-=-=-=-=-=-=-=-|" + reset;
//    }
//    public static String menuComuns(){
//        return "\n\n|-=-=-=-=-=-=-=-=-「 ✦ Tabelas ✦ 」=-=-=-=-=-=-=-=-=-|" +
//                "\n[01] - Endereços" +
//                "\n[02] - Telefones" +
//                "\n[03] - Responsáveis" +
//                "\n[04] - Responsaveis_Telefones" +
//                "\n[05] - Responsaveis_Roles" +
//                "\n[06] - Transportadores" +
//                "\n[07] - Transportadores_Telefones" +
//                "\n[08] - Fotos" +
//                "\n[09] - Vans" +
//                "\n[10] - Alunos" +
//                "\n[11] - Rotas" +
//                "\n[12] - Voltar ao inicio" +
//                "\n|-=-=-=-=-=-=-=-=-=「 ✦ TechMovee ✦ 」=-=-=-=-=-=-=-=-=-=-|";}
//    public static String funcoesSQL(){
//        return "\n\n|-=-=-=-=-=-=-=-=-「 ✦ Funções ✦ 」=-=-=-=-=-=-=-=-=-|" +
//                "\n[01] - Select" +
//                "\n[02] - Insert" +
//                "\n[03] - Update" +
//                "\n[04] - Delete" +
//                "\n[05] - Codigo livre" +
//                "\n[06] - Voltar para as tabelas" +
//                "\n|-=-=-=-=-=-=-=-=-=「 ✦ TechMovee ✦ 」=-=-=-=-=-=-=-=-=-=-|";}
//    public static String menuAdmins(){
//        return "\n\n|-=-=-=-=-=-=-=-=-「 ✦ Tabelas ✦ 」=-=-=-=-=-=-=-=-=-|" +
//                "\n[01] - Admins" +
//                "\n[02] - Roles" +
//                "\n[03] - Admins_Roles" +
//                "\n[04] - Responsaveis_Roles" +
//                "\n[05] - Voltar ao inicio" +
//                "\n|-=-=-=-=-=-=-=-=-=「 ✦ TechMovee ✦ 」=-=-=-=-=-=-=-=-=-=-|";
//    }
//    public static String load(){
//        return "\nLOADING...\n";
//    }
//    public static void time(int seg){
//        try {
//            sleep(seg* 1000L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

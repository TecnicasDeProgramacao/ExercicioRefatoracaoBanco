package com.bcopstein.ExercicioRefatoracaoBanco;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import com.bcopstein.ExercicioRefatoracaoBanco.StatusContaFactory.Tipo;

public class Persistencia 
{
	private static Persistencia instance = null;
    private final String NomeBDContas = "BDContasBNG.txt";
    private final String NomeBDOperacoes = "BDOperBNG.txt";
    
    private Persistencia()
    {
    }
    
    public static Persistencia getInstance()
    {
    	if(instance == null)
    	{
    		instance = new Persistencia();
    	}
    	return instance;
    }
    
    public Map<Integer,Conta> loadContas()
    {
    	Map<Integer,Conta> contas = new HashMap<>();
    	
        String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir+"\\"+NomeBDContas;
        System.out.println(nameComplete);
        Path path2 = Paths.get(nameComplete); 
        try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset()))){ 
           sc.useDelimiter("[;\n]"); // separadores: ; e nova linha 
           int numero;
           String nomeCorr;
           double saldo;
           int status;
           while (sc.hasNext()){ 
               numero = Integer.parseInt(sc.next()); 
               nomeCorr = sc.next();
               saldo = Double.parseDouble(sc.next());
               status = Integer.parseInt(sc.next());
               Conta conta;
               switch(status)
               {
               		case 0: conta = new Conta(numero,nomeCorr,saldo, StatusContaFactory.instance().createStatus(Tipo.SILVER)); break;
               		case 1: conta = new Conta(numero,nomeCorr,saldo, StatusContaFactory.instance().createStatus(Tipo.GOLD)); break;
               		case 2: conta = new Conta(numero,nomeCorr,saldo, StatusContaFactory.instance().createStatus(Tipo.PLATINUM)); break;
               		default: throw new IllegalArgumentException();
               }
               
               contas.put(numero, conta);
           }
        }catch (IOException x){ 
            System.err.format("Erro de E/S: %s%n", x);
            return null;
        } 
        return contas;
    }

    public void saveContas(Map<Integer,Conta> contas) 
    {
        Path path1 = Paths.get(NomeBDContas); 
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) 
        { 
            for(Map.Entry<Integer, Conta> entry: contas.entrySet()) {
            	int st = 0;
            	Conta c = entry.getValue();
            	if(c.getStatus() instanceof Silver)
            	{
            		st = 0;
            	}
            	else if(c.getStatus() instanceof Gold)
            	{
            		st = 1;
            	}
            	else if(c.getStatus() instanceof Platinum)
            	{
            		st = 2;
            	}
            	else
            	{
            		throw new IllegalArgumentException("Status invalido");
            	}
                writer.format(Locale.ENGLISH,
                		      "%d;%s;%f;%d;",
                		      c.getNumero(),c.getCorrentista(), 
                              c.getSaldo(),st); 
            }
        } 
        catch (IOException x) 
        { 
            System.err.format("Erro de E/S: %s%n", x); 
        } 
    }

    public void saveOperacoes(Collection<Operacao> operacoes) 
    {
        Path path1 = Paths.get(NomeBDOperacoes); 
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) 
        { 
            for(Operacao op:operacoes)
            {
            	int st = 0;
            	if(op.getStatusConta() instanceof Silver)
            	{
            		st = 0;
            	}
            	else if(op.getStatusConta() instanceof Gold)
            	{
            		st = 1;
            	}
            	else if(op.getStatusConta() instanceof Platinum)
            	{
            		st = 2;
            	}
            	else
            	{
            		throw new IllegalArgumentException("Status invalido");
            	}
                writer.format(Locale.ENGLISH,
                		      "%d;%d;%d;%d;%d;%d;%d;%d;%f;%d;",  
                              op.getDia(),op.getMes(),op.getAno(),
                              op.getHora(),op.getMinuto(),op.getSegundo(),
                              op.getNumeroConta(),st,
                              op.getValorOperacao(),op.getTipoOperacao()
                             ); 
            }
        } 
        catch (IOException x) 
        { 
            System.err.format("Erro de E/S: %s%n", x); 
        } 
    }
    
    public List<Operacao> loadOperacoes()
    {
        List<Operacao> operacoes = new LinkedList<Operacao>();
        
    	String currDir = Paths.get("").toAbsolutePath().toString();
        String nameComplete = currDir+"\\"+NomeBDOperacoes;
        System.out.println(nameComplete);
        Path path2 = Paths.get(nameComplete); 
        try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset())))
        { 
           sc.useDelimiter("[;\n]"); // separadores: ; e nova linha 
           int dia,mes,ano;
           int hora,minuto,segundo;
           int numero,status,tipo;
           double valor;
       
           while (sc.hasNext())
           { 
               dia = Integer.parseInt(sc.next()); 
               mes = Integer.parseInt(sc.next()); 
               ano = Integer.parseInt(sc.next()); 
               hora = Integer.parseInt(sc.next()); 
               minuto = Integer.parseInt(sc.next()); 
               segundo = Integer.parseInt(sc.next()); 
               numero = Integer.parseInt(sc.next()); 
               status = Integer.parseInt(sc.next()); 
               valor = Double.parseDouble(sc.next());
               tipo = Integer.parseInt(sc.next());
               Status st;
               switch(status)
               {
               		case 0: st = StatusContaFactory.instance().createStatus(Tipo.SILVER); break; 
               		case 1: st = StatusContaFactory.instance().createStatus(Tipo.GOLD); break;
               		case 2: st = StatusContaFactory.instance().createStatus(Tipo.PLATINUM); break;
               		default: throw new IllegalArgumentException();
               }
               Operacao op = new Operacao(
            		   dia, mes, ano,
            		   hora, minuto, segundo,
	                   numero, st,
	                   valor, tipo);
               
               operacoes.add(op);
           }
        }
        catch (IOException x)
        { 
            System.err.format("Erro de E/S: %s%n", x);
            return null;
        } 
        return operacoes;    	
    }
}

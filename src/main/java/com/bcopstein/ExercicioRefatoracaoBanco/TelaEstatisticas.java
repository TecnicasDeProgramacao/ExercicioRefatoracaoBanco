package com.bcopstein.ExercicioRefatoracaoBanco;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaEstatisticas 
{
	private Stage mainStage; 
	private Scene cenaEntrada;
	private Scene cenaOperacoes;
	private List<Operacao> operacoes;
	private ObservableList<Operacao> operacoesConta;
	private Conta conta;
	
	public TelaEstatisticas(Stage mainStage, Scene telaOperacoes, Conta conta, List<Operacao> operacoes) 
	{
		this.mainStage = mainStage;
		this.cenaOperacoes = telaOperacoes;
		this.conta = conta;
		this.operacoes = operacoes;
	}
	
	@SuppressWarnings("restriction")
	public Scene getTelaEstatisticas()
	{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene cenaEstatisticas = new Scene(grid);
		
		String dadosCorr = conta.getNumero()+" : "+conta.getCorrentista();
		Text scenetitle = new Text(dadosCorr);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);
		
		DatePicker dp = new DatePicker();
		
		grid.add(dp, 2, 1);
		
		Label saldoMedioMes = new Label();
		grid.add(saldoMedioMes, 2, 2);
		
		Label credits = new Label();
		Label debits = new Label();
		grid.add(credits, 2, 3);
		grid.add(debits, 2, 4);
		
		GregorianCalendar date = new GregorianCalendar();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date.get(GregorianCalendar.YEAR) + "-" + 
											 (date.get(GregorianCalendar.MONTH)+1) + "-" + 
											  date.get(GregorianCalendar.DAY_OF_MONTH), formatter);
		
		dp.setOnAction(e ->
		{
			LocalDate ld = dp.getValue();
			int mes = ld.getMonthValue();
			int ano = ld.getYear();
			ArrayList<Double> saldosDias = new ArrayList<Double>();
			double totalMes = 0;
			int day = 1;
			double saldoDia = 0;
			int creditos = 0, debitos = 0;
			ArrayList<Operacao> opsMes = new ArrayList<Operacao>();
			for(Operacao op: this.operacoes)
			{
				if(op.getNumeroConta() == this.conta.getNumero())
				{
					if(op.getAno() < ano || op.getMes() < mes)
					{
						
						if(op.getTipoOperacao() == 0) //CRÉDITO
						{
							saldoDia += op.getValorOperacao();
						}
						else //DÉBITO
						{
							saldoDia -= op.getValorOperacao();
						}
					}
					else
					{
						opsMes.add(op);
					}
				}
			}
			
			if(opsMes.isEmpty())
			{
				for(int i = 1; i < 31; i++)
				{
					saldosDias.add(saldoDia);
				}
			}
			else
			{
				for(Operacao op: opsMes)
				{
					while(op.getDia() > day)
					{
						saldosDias.add(saldoDia);
						day++;
					}
					
					if(op.getTipoOperacao() == 0)
					{
						saldoDia += op.getValorOperacao();
						creditos++;
					}
					else
					{
						saldoDia -= op.getValorOperacao();
						debitos++;
					}
				}
				while(saldosDias.size() < 30)
				{
					saldosDias.add(saldoDia);
				}
			}
			for(Double d: saldosDias)
			{
				totalMes += d;
			}
			double media = totalMes/30.0;
			saldoMedioMes.setText("Saldo Médio do Mês: " + media);
			credits.setText("Créditos: " + creditos);
			debits.setText("Débitos: " + debitos);
			
		});
		
		dp.setValue(localDate);
		
		Button btnVoltar = new Button("Voltar");
		HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.TOP_CENTER);
		hbBtn.getChildren().add(btnVoltar);
		grid.add(hbBtn, 2, 5);
		
		btnVoltar.setOnAction(e ->
		{
			mainStage.setScene(cenaOperacoes);
		});
		
		String categoria = "Categoria: "+conta.getStrStatus();
		String limRetDiaria = "Limite retirada diaria: "+conta.getLimRetiradaDiaria();

		Label cat = new Label(categoria);
		grid.add(cat, 0, 1);

		Label lim = new Label(limRetDiaria);
		grid.add(lim, 0, 2);
		
		return cenaEstatisticas;
	}
}

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
	private Scene cenaOperacoes;
	
	public TelaEstatisticas(Stage mainStage, Scene telaOperacoes) 
	{
		this.mainStage = mainStage;
		this.cenaOperacoes = telaOperacoes;
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
		
		String dadosCorr = LogicaOperacoes.instance().getNumeroConta()+" : "+LogicaOperacoes.instance().getNomeCorrente();
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
		
		dp.setValue(localDate);
		
		Button btnVoltar = new Button("Voltar");
		HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.TOP_CENTER);
		hbBtn.getChildren().add(btnVoltar);
		grid.add(hbBtn, 2, 5);		
		
		String categoria = "Categoria: "+LogicaOperacoes.instance().getStatusConta();
		String limRetDiaria = "Limite retirada diaria: "+LogicaOperacoes.instance().getLimRet();

		Label cat = new Label(categoria);
		grid.add(cat, 0, 1);

		Label lim = new Label(limRetDiaria);
		grid.add(lim, 0, 2);
		
		dp.setOnAction(e ->
		{
			LocalDate ld = dp.getValue();
			int mes = ld.getMonthValue();
			int ano = ld.getYear();
			
			
			double medio = LogicaOperacoes.instance().solicitaSaldoMedio(mes, ano);
			int creditos = LogicaOperacoes.instance().totalCreditos(mes, ano); 
			int debitos = LogicaOperacoes.instance().totalDebitos(mes, ano);
			
			saldoMedioMes.setText("Saldo Médio do Mês: " + medio);
			credits.setText("Créditos: " + creditos);
			debits.setText("Débitos: " + debitos);
			
		});
		
		btnVoltar.setOnAction(e ->
		{
			mainStage.setScene(cenaOperacoes);
		});
		
		return cenaEstatisticas;
	}
}

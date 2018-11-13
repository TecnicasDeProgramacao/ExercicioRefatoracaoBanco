package com.bcopstein.ExercicioRefatoracaoBanco;

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
		
		double saldoDia = 0;
		double saldoMes = 0;
		GregorianCalendar date = new GregorianCalendar();
		for(Operacao o: this.operacoes)
		{
			if(o.getNumeroConta() == this.conta.getNumero() && o.getMes() == date.get(GregorianCalendar.MONTH)+1 && o.getAno() == date.get(GregorianCalendar.YEAR))
			{
				
			}
		}
		
		String categoria = "Categoria: "+conta.getStrStatus();
		String limRetDiaria = "Limite retirada diaria: "+conta.getLimRetiradaDiaria();

		Label cat = new Label(categoria);
		grid.add(cat, 0, 1);

		Label lim = new Label(limRetDiaria);
		grid.add(lim, 0, 2);
		
		return cenaEstatisticas;
	}
}

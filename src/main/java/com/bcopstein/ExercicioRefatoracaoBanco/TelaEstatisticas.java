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
		return null;
	}
}

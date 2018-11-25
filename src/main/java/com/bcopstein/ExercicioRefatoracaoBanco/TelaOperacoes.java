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

public class TelaOperacoes {
	private Stage mainStage; 
	private Scene cenaEntrada;
	private Scene cenaOperacoes;
	private List<Operacao> operacoes;
	private ObservableList<Operacao> operacoesConta;

	private Conta conta; 

	private TextField tfValorOperacao;
	private TextField tfSaldo;

	public TelaOperacoes(Stage mainStage, Scene telaEntrada, List<Operacao> operacoes) { // Tirar esse parâmetro																					// conta
		this.mainStage = mainStage;
		this.cenaEntrada = telaEntrada;
		//this.conta = conta;
		this.operacoes = operacoes;
	}

	public Scene getTelaOperacoes() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		String dadosCorr = LogicaOperacoes.getNumeroConta()+" : "+LogicaOperacoes.getNomeCorrent();
		Text scenetitle = new Text(dadosCorr);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		String categoria = "Categoria: "+LogicaOperacoes.getStatusConta();
		String limRetDiaria = "Limite retirada diaria: "+ LogicaOperacoes.getLimRet();

		Label cat = new Label(categoria);
		grid.add(cat, 0, 1);

		Label lim = new Label(limRetDiaria);
		grid.add(lim, 0, 2);

		Label tit = new Label("Ultimos movimentos");
		grid.add(tit,0,3);

		// Seleciona apenas o extrato da conta atual
		List<Operacao> lst = LogicaOperacoes.solicitaExtrato();
 		operacoesConta = FXCollections.observableArrayList(LogicaOperacoes.solicitaExtrato());

		ListView<Operacao> extrato = new ListView<>(operacoesConta);
		extrato.setPrefHeight(140);
		grid.add(extrato, 0, 4);

		tfSaldo = new TextField();
		tfSaldo.setDisable(true);
		tfSaldo.setText(""+LogicaOperacoes.solicitaSaldo());
		HBox valSaldo = new HBox(20);        
		valSaldo.setAlignment(Pos.BOTTOM_LEFT);
		valSaldo.getChildren().add(new Label("Saldo"));
		valSaldo.getChildren().add(tfSaldo);
		grid.add(valSaldo, 0, 5);        

		tfValorOperacao = new TextField();
		HBox valOper = new HBox(30);        
		valOper.setAlignment(Pos.BOTTOM_CENTER);
		valOper.getChildren().add(new Label("Valor operacao"));
		valOper.getChildren().add(tfValorOperacao);
		grid.add(valOper, 1, 1);        

		Button btnCredito = new Button("Credito");
		Button btnDebito = new Button("Debito");
		Button btnVoltar = new Button("Voltar");
		Button btnEst = new Button("Estatísticas");
		HBox hbBtn = new HBox(20);
		hbBtn.setAlignment(Pos.TOP_CENTER);
		hbBtn.getChildren().add(btnCredito);
		hbBtn.getChildren().add(btnDebito);
		hbBtn.getChildren().add(btnVoltar);
		hbBtn.getChildren().add(btnEst);
		grid.add(hbBtn, 1, 2);

		btnCredito.setOnAction(e->{
			try {
				double valor = Integer.parseInt(tfValorOperacao.getText());
				boolean x = LogicaOperacoes.operacaoCredito(valor);
				/*if (valor < 0.0) {
					throw new NumberFormatException("Valor invalido");
				}
				//String statusAnt = conta.getStrStatus();
				conta.deposito(valor);
				cat.setText("Categoria: " + conta.getStrStatus());

				GregorianCalendar date = new GregorianCalendar();
				Operacao op = new Operacao(
						date.get(GregorianCalendar.DAY_OF_MONTH),
						date.get(GregorianCalendar.MONTH)+1,
						date.get(GregorianCalendar.YEAR),
						date.get(GregorianCalendar.HOUR),
						date.get(GregorianCalendar.MINUTE),
						date.get(GregorianCalendar.SECOND),
						conta.getNumero(),
						conta.getStatus(),
						valor,
						0);
				operacoes.add(op);*/        	  
				tfSaldo.setText(""+LogicaOperacoes.solicitaSaldo());
				operacoesConta = FXCollections.observableArrayList(LogicaOperacoes.solicitaExtrato());

			}catch(NumberFormatException ex) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Valor inválido !!");
				alert.setHeaderText(null);
				alert.setContentText("Valor inválido para operacao de crédito!!");

				alert.showAndWait();
			}        	
		});

		btnDebito.setOnAction(e->{
			try {
				double valor = Integer.parseInt(tfValorOperacao.getText());
				LogicaOperacoes.operacaoDebito(valor);
					// Esta adicionando em duas listas (resolver na camada de negocio)					 	  
					tfSaldo.setText(""+conta.getSaldo());
					//operacoesConta.add(op);
					tfSaldo.setText(""+conta.getSaldo());
				}
			catch(NumberFormatException ex) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Valor inválido !!");
				alert.setHeaderText(null);
				alert.setContentText("Valor inválido para operacao de débito!");

				alert.showAndWait();
			}        	
		});
		
		btnEst.setOnAction(e -> 
		{
			TelaEstatisticas test = new TelaEstatisticas(this.mainStage, this.cenaOperacoes,conta,operacoes);
			Scene scene = test.getTelaEstatisticas();
			mainStage.setScene(scene);
		});

		btnVoltar.setOnAction(e->{
			mainStage.setScene(cenaEntrada);
		});

		cenaOperacoes = new Scene(grid);
		return cenaOperacoes;
	}

}

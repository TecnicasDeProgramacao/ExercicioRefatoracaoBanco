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
	private ObservableList<Operacao> operacoesConta;

	private TextField tfValorOperacao;
	private TextField tfSaldo;

	public TelaOperacoes(Stage mainStage, Scene telaEntrada) { // Tirar esse parâmetro																					// conta
		this.mainStage = mainStage;
		this.cenaEntrada = telaEntrada;
	}

	public Scene getTelaOperacoes() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		String dadosCorr = LogicaOperacoes.instance().getNumeroConta()+" : "+LogicaOperacoes.instance().getNomeCorrente();
		Text scenetitle = new Text(dadosCorr);
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		String categoria = "Categoria: "+LogicaOperacoes.instance().getStatusConta();
		String limRetDiaria = "Limite retirada diaria: "+ LogicaOperacoes.instance().getLimRet();

		Label cat = new Label(categoria);
		grid.add(cat, 0, 1);

		Label lim = new Label(limRetDiaria);
		grid.add(lim, 0, 2);

		Label tit = new Label("Ultimos movimentos");
		grid.add(tit,0,3);

		// Seleciona apenas o extrato da conta atual
		List<Operacao> lst = LogicaOperacoes.instance().solicitaExtrato();
 		operacoesConta = FXCollections.observableArrayList(LogicaOperacoes.instance().solicitaExtrato());

		ListView<Operacao> extrato = new ListView<>(operacoesConta);
		extrato.setPrefHeight(140);
		grid.add(extrato, 0, 4);

		tfSaldo = new TextField();
		tfSaldo.setDisable(true);
		tfSaldo.setText(""+LogicaOperacoes.instance().solicitaSaldo());
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
				Operacao op = LogicaOperacoes.instance().operacaoCredito(valor);
				     	  
				tfSaldo.setText(""+LogicaOperacoes.instance().solicitaSaldo());
				operacoesConta.add(op);				

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
				Operacao op =LogicaOperacoes.instance().operacaoDebito(valor);
					tfSaldo.setText(""+LogicaOperacoes.instance().solicitaSaldo());
					operacoesConta.add(op);				
				}
			catch(NumberFormatException ex) 
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Valor inválido !!");
				alert.setHeaderText(null);
				alert.setContentText("Valor inválido para operacao de débito!");

				alert.showAndWait();
			}
			catch(IllegalArgumentException ex)
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Valor inválido !!");
				alert.setHeaderText(null);
				alert.setContentText("Valor de debito ultrapassa limite diario!");

				alert.showAndWait();
			}
		});
		
		btnEst.setOnAction(e -> 
		{
			TelaEstatisticas test = new TelaEstatisticas(this.mainStage, this.cenaOperacoes);
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

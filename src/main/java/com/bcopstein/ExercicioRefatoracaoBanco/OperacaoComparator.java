package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.Comparator;

public class OperacaoComparator implements Comparator<Operacao> {

	public OperacaoComparator() 
	{
	}
	
	public int compare(Operacao op1, Operacao op2)
    {
        if(op1.getAno() == op2.getAno())
        {
            if(op1.getMes() == op2.getMes())
            {
                if(op1.getDia() == op2.getDia())
                {
                    if(op1.getHora() == op2.getHora())
                    {
                        if(op1.getMinuto() == op2.getMinuto())
                        {
                            if(op1.getSegundo() == op2.getSegundo())
                            {
                                return 0;
                            }
                            else
                            {
                                return op1.getSegundo() - op2.getSegundo();
                            }
                        }
                        else
                        {
                            return op1.getMinuto() - op2.getMinuto();
                        }
                    }
                    else
                    {
                        return op1.getHora() - op2.getHora();
                    }
                }
                else
                {
                    return op1.getDia() - op2.getDia();
                }
            }
            else
            {
                return op1.getMes() - op2.getMes();
            }
        }
        else
        {
            return op1.getAno() - op2.getAno();
        }
    }
}

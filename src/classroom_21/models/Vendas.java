package classroom_21.models;

import java.util.Date;

public class Vendas {

    private int id;
    private int idVeiculo;
    private Date dataHora;
    
    private Veiculo veiculo;

    public Vendas() {
    }

    public Vendas(int id, int idVeiculo, Date dataHora) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.dataHora = dataHora;
    }

    public Vendas(int id, int idVeiculo, Date dataHora, Veiculo veiculo) {
        this.id = id;
        this.idVeiculo = idVeiculo;
        this.dataHora = dataHora;
        this.veiculo = veiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}

package com.example.olina.gasosa.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Danilo Cruz on 03/03/2018.
 */

@Entity
public class Entrada {
    @PrimaryKey(autoGenerate = true)
    private long uid;

    @ColumnInfo(name = "data")
    private Date data;

    @ColumnInfo(name = "valor_pago")
    private double valorPago;

    @ColumnInfo(name = "litragem")
    private double litragem;

    @ColumnInfo(name = "quilometragem")
    private long quilometragem;

    public Entrada() {

    }

    public Entrada(long uid, Date data, double litragem, long quilometragem) {
        this.uid = uid;
        this.data = data;
        this.litragem = litragem;
        this.quilometragem = quilometragem;
    }


    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public double getLitragem() {
        return litragem;
    }

    public void setLitragem(double litragem) {
        this.litragem = litragem;
    }

    public long getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(long quilometragem) {
        this.quilometragem = quilometragem;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data) + '\n' + "R$ " + valorPago + " | " + litragem + "L | " + quilometragem + " km";
    }
}

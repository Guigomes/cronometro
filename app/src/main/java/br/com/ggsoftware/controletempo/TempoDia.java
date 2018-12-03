package br.com.ggsoftware.controletempo;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
@Entity(indexes = {
        @Index(value = "dia DESC", unique = true)
})
public class TempoDia {


    @Id
    private Long id;

    @NotNull
    private Date dia;

    @NotNull
    private long tempoSentado;

    @NotNull
    private long tempoEmPe;

    @NotNull
    private long tempoEmFora;

@Generated(hash = 1924549841)
public TempoDia(Long id, @NotNull Date dia, long tempoSentado, long tempoEmPe,
        long tempoEmFora) {
    this.id = id;
    this.dia = dia;
    this.tempoSentado = tempoSentado;
    this.tempoEmPe = tempoEmPe;
    this.tempoEmFora = tempoEmFora;
}

@Generated(hash = 1088082639)
public TempoDia() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public Date getDia() {
    return this.dia;
}

public void setDia(Date dia) {
    this.dia = dia;
}

public long getTempoSentado() {
    return this.tempoSentado;
}

public void setTempoSentado(long tempoSentado) {
    this.tempoSentado = tempoSentado;
}

public long getTempoEmPe() {
    return this.tempoEmPe;
}

public void setTempoEmPe(long tempoEmPe) {
    this.tempoEmPe = tempoEmPe;
}

public long getTempoEmFora() {
    return this.tempoEmFora;
}

public void setTempoEmFora(long tempoEmFora) {
    this.tempoEmFora = tempoEmFora;
}


}

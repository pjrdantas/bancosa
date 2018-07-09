package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbBanco;



public interface ITbBancoService {
	
    void addTbBanco(TbBanco tbBanco);
    void updateTbBanco(TbBanco tbBanco);	
    List<TbBanco> consultar();
    TbBanco getTbBancoById(int id);
    void deleteTbBanco(int id);
}

package br.com.meuBanco.service.impl;

import java.util.List;

import br.com.meuBanco.entity.TbBanco;
import br.com.meuBanco.entity.dto.TbBancoDTO;





public interface ITbBancoService {
	
    void addTbBanco(TbBanco tbBanco);
    void updateTbBanco(TbBanco tbBanco);	
    List<TbBancoDTO> consultar();
    TbBancoDTO getTbBancoById(int id);
    void deleteTbBanco(int id);
}

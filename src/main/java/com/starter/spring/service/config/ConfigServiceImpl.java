package com.starter.spring.service.config;

import java.util.Arrays;
import java.util.List;

import com.starter.spring.enums.TipoExameEnum;
import com.starter.spring.enums.TipoUsuario;
import org.springframework.stereotype.Service;

import com.starter.spring.model.Parametro;
import com.starter.spring.model.Perfil;
import com.starter.spring.model.TipoExame;
import com.starter.spring.repository.ParametroRepository;
import com.starter.spring.repository.PerfilRepository;
import com.starter.spring.repository.TipoExameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final PerfilRepository repository;

    private final TipoExameRepository tipoExameRepository;

    private final ParametroRepository parametroRepository;

    @Override
    public void instanciaPerfil() {

        Perfil p1 = Perfil.builder().nome(TipoUsuario.ADMINSTRATIVO.getDescricao()).build();
        Perfil p2 = Perfil.builder().nome(TipoUsuario.ADMINSTRADOR.getDescricao()).build();
        Perfil p3 = Perfil.builder().nome(TipoUsuario.MEDICO.getDescricao()).build();
        Perfil p4 = Perfil.builder().nome(TipoUsuario.BIOMEDICO.getDescricao()).build();
        Perfil p5 = Perfil.builder().nome(TipoUsuario.PACIENTE.getDescricao()).build();
        Perfil p6 = Perfil.builder().nome(TipoUsuario.ENFERMEIRO.getDescricao()).build();
        Perfil p7 = Perfil.builder().nome(TipoUsuario.TECNICO_ENFERMAGEM.getDescricao()).build();

        repository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7));
    }

    @Override
    public void instanciaTipoExame() {

        TipoExame p1 = TipoExame.builder()
                .id(1L)
                .nome(TipoExameEnum.SANGUE.getDescricao())
                .descricao("Exame de Sangue")
                .build();

        TipoExame p2 = TipoExame.builder()
                .id(2L)
                .nome(TipoExameEnum.URINA.getDescricao())
                .descricao("Exame de Urina")
                .build();

        tipoExameRepository.saveAll(Arrays.asList(p1, p2));
    }

    @Override
    public void instanciaParametro() {

        List<Parametro> parametrosExameSangue = this.parametrosExameSangue();

        parametroRepository.saveAll(parametrosExameSangue);
    }

    @Override
    public Boolean perfisInstanciados() {
        return repository.count() > 0;
    }

    @Override
    public Boolean tipoExameInstanciados() {
        return tipoExameRepository.count() > 0;
    }

    @Override
    public Boolean parametrosInstanciados() {
        return parametroRepository.count() > 0;
    }

    public List<Parametro> parametrosExameSangue() {
        Parametro hemacias = Parametro.builder()
                .nome("Hemácias")
                .unidadeDeMedida("milhões/mm³")
                .valorReferenciaMinimo("4.5")
                .valorReferenciaMaximo("5.5")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro hemoglobina = Parametro.builder()
                .nome("Hemoglobina")
                .unidadeDeMedida("g/dL")
                .valorReferenciaMinimo("12.0")
                .valorReferenciaMaximo("16.0")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro hematocrito = Parametro.builder()
                .nome("Hematócrito")
                .unidadeDeMedida("%")
                .valorReferenciaMinimo("36")
                .valorReferenciaMaximo("50")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro leucocitos = Parametro.builder()
                .nome("Leucócitos")
                .unidadeDeMedida("/mm³")
                .valorReferenciaMinimo("4000")
                .valorReferenciaMaximo("11000")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro plaquetas = Parametro.builder()
                .nome("Plaquetas")
                .unidadeDeMedida("mil/mm³")
                .valorReferenciaMinimo("150")
                .valorReferenciaMaximo("400")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro glicose = Parametro.builder()
                .nome("Glicose")
                .unidadeDeMedida("mg/dL")
                .valorReferenciaMinimo("70")
                .valorReferenciaMaximo("99")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro colesterolTotal = Parametro.builder()
                .nome("Colesterol Total")
                .unidadeDeMedida("mg/dL")
                .valorReferenciaMinimo("0")
                .valorReferenciaMaximo("200")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro colesterolHDL = Parametro.builder()
                .nome("Colesterol HDL")
                .unidadeDeMedida("mg/dL")
                .valorReferenciaMinimo("40")
                .valorReferenciaMaximo("60")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro triglicerideos = Parametro.builder()
                .nome("Triglicerídeos")
                .unidadeDeMedida("mg/dL")
                .valorReferenciaMinimo("0")
                .valorReferenciaMaximo("150")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        Parametro creatinina = Parametro.builder()
                .nome("Creatinina")
                .unidadeDeMedida("mg/dL")
                .valorReferenciaMinimo("0.7")
                .valorReferenciaMaximo("1.3")
                .tipoExame(tipoExameRepository.findById(1L).get())
                .build();

        return Arrays.asList(hemoglobina, hemacias, hematocrito, leucocitos, plaquetas, glicose, colesterolTotal,
                colesterolHDL, triglicerideos, creatinina);
    }

}

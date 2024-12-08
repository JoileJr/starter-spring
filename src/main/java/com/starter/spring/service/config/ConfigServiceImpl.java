package com.starter.spring.service.config;

import java.util.ArrayList;
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

                List<Parametro> parametrosExameSangue = new ArrayList<>();
                parametrosExameSangue.addAll(this.parametrosExameSangue());
                parametrosExameSangue.addAll(this.parametrosExameUrina());

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
                Parametro hemoglobina = Parametro.builder()
                                .id(1L)
                                .nome("Hemoglobina")
                                .unidadeDeMedida("g/dL")
                                .valorReferenciaMinimo("12.0")
                                .valorReferenciaMaximo("16.0")
                                .mascara("99.9")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro hemacias = Parametro.builder()
                                .id(2L)
                                .nome("Hemácias")
                                .unidadeDeMedida("milhões/mm³")
                                .valorReferenciaMinimo("4.5")
                                .valorReferenciaMaximo("5.5")
                                .mascara("9.9")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro hematocrito = Parametro.builder()
                                .id(3L)
                                .nome("Hematócrito")
                                .unidadeDeMedida("%")
                                .valorReferenciaMinimo("36")
                                .valorReferenciaMaximo("50")
                                .mascara("99")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro leucocitos = Parametro.builder()
                                .id(4L)
                                .nome("Leucócitos")
                                .unidadeDeMedida("/mm³")
                                .valorReferenciaMinimo("4000")
                                .valorReferenciaMaximo("11000")
                                .mascara("99999")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro plaquetas = Parametro.builder()
                                .id(5L)
                                .nome("Plaquetas")
                                .unidadeDeMedida("mil/mm³")
                                .valorReferenciaMinimo("150")
                                .valorReferenciaMaximo("400")
                                .mascara("999")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro glicose = Parametro.builder()
                                .id(6L)
                                .nome("Glicose")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("70")
                                .valorReferenciaMaximo("99")
                                .mascara("999")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro colesterolTotal = Parametro.builder()
                                .id(7L)
                                .nome("Colesterol Total")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("200")
                                .mascara("999")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro colesterolHDL = Parametro.builder()
                                .id(8L)
                                .nome("Colesterol HDL")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("40")
                                .valorReferenciaMaximo("60")
                                .mascara("999")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro triglicerideos = Parametro.builder()
                                .id(9L)
                                .nome("Triglicerídeos")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("150")
                                .mascara("999")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                Parametro creatinina = Parametro.builder()
                                .id(10L)
                                .nome("Creatinina")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("0.7")
                                .valorReferenciaMaximo("1.3")
                                .mascara("9.9")
                                .tipoExame(tipoExameRepository.findById(1L).get())
                                .build();

                return Arrays.asList(hemacias, hemoglobina, hematocrito, leucocitos, plaquetas,
                                glicose, colesterolTotal, colesterolHDL, triglicerideos, creatinina);
        }

        public List<Parametro> parametrosExameUrina() {
                Parametro densidade = Parametro.builder()
                                .id(11L)
                                .nome("Densidade")
                                .unidadeDeMedida("grandezas s/u.m.")
                                .valorReferenciaMinimo("1.005")
                                .valorReferenciaMaximo("1.030")
                                .mascara("9.999")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                Parametro ph = Parametro.builder()
                                .id(12L)
                                .nome("pH")
                                .unidadeDeMedida("grandezas s/u.m.")
                                .valorReferenciaMinimo("4.5")
                                .valorReferenciaMaximo("8.0")
                                .mascara("9.9")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                Parametro proteina = Parametro.builder()
                                .id(13L)
                                .nome("Proteína")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("10")
                                .mascara("99")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                Parametro glicoseUrina = Parametro.builder()
                                .id(14L)
                                .nome("Glicose")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("15")
                                .mascara("99")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                Parametro leucocitosUrina = Parametro.builder()
                                .id(15L)
                                .nome("Leucócitos")
                                .unidadeDeMedida("/campo")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("5")
                                .mascara("9")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                Parametro eritrocitos = Parametro.builder()
                                .id(16L)
                                .nome("Eritrócitos")
                                .unidadeDeMedida("/campo")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("2")
                                .mascara("9")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                Parametro cetonas = Parametro.builder()
                                .id(17L)
                                .nome("Cetonas")
                                .unidadeDeMedida("mg/dL")
                                .valorReferenciaMinimo("0")
                                .valorReferenciaMaximo("5")
                                .mascara("9")
                                .tipoExame(tipoExameRepository.findById(2L).get())
                                .build();

                return Arrays.asList(densidade, ph, proteina, glicoseUrina, leucocitosUrina, eritrocitos, cetonas);
        }

}

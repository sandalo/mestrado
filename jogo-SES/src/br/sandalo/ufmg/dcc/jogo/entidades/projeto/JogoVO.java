package br.sandalo.ufmg.dcc.jogo.entidades.projeto;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class JogoVO {

	private static JogoVO INSTANCE;

	public static JogoVO getInstance() {
		if (INSTANCE == null) {
			synchronized(JogoVO.class){
				if (INSTANCE == null) {
					INSTANCE = new JogoVO();
				}
			}
		}
		return INSTANCE;
	}

	private JogoVO() {
		QualificacaoVO especificacao = new QualificacaoVO(1, "Especificação", 1, "/imagens/especific.png");
		QualificacaoVO analise = new QualificacaoVO(1, "Analise", 2, "/imagens/analise.png");
		QualificacaoVO arquitetura = new QualificacaoVO(4, "Arquitetura", 3, "/imagens/architect.png");
		QualificacaoVO teste = new QualificacaoVO(3, "Teste", 6, "/imagens/teste.png");
		QualificacaoVO codificacao = new QualificacaoVO(15, "Codificação", 4, "/imagens/codificacao.png");
		QualificacaoVO inspecao = new QualificacaoVO(15, "Inspeção", 5, "/imagens/inspecao.png");
		QualificacaoVO integracao = new QualificacaoVO(5, "Integração", 7, "/imagens/integracao.png");

		qualificacoesDisponiveisNoJogo.add(especificacao);
		qualificacoesDisponiveisNoJogo.add(analise);
		qualificacoesDisponiveisNoJogo.add(arquitetura);
		qualificacoesDisponiveisNoJogo.add(teste);
		qualificacoesDisponiveisNoJogo.add(codificacao);
		qualificacoesDisponiveisNoJogo.add(inspecao);
		qualificacoesDisponiveisNoJogo.add(integracao);

		ProfissionalDeTIVO profissional = new ProfissionalDeTIVO();
		profissional.setNome("Ronaldo Nazário");
		profissional.setExperiencia(10);
		profissional.setAgilidade(10);
		profissional.setSalario(50L);
		profissional.setUrlFoto("/imagens/ronaldo_fenomeno.jpg");
		profissional.getQualificacoes().add(codificacao);
		profissional.getQualificacoes().add(teste);
		profissional.getQualificacoes().add(inspecao);
		profissional.getQualificacoes().add(integracao);

		profissionais.add(profissional);

		profissional = new ProfissionalDeTIVO();
		profissional.setNome("Ronaldo gaucho");
		profissional.setExperiencia(10);
		profissional.setAgilidade(10);
		profissional.setSalario(50L);
		profissional.setUrlFoto("/imagens/ronaldo.jpg");
		profissional.getQualificacoes().add(especificacao);
		profissional.getQualificacoes().add(analise);
		profissional.getQualificacoes().add(arquitetura);
		profissional.getQualificacoes().add(codificacao);

		profissionais.add(profissional);

	}

	private Set<ProfissionalDeTIVO> profissionais = new TreeSet<ProfissionalDeTIVO>();
	private Set<QualificacaoVO> qualificacoesDisponiveisNoJogo = new HashSet<QualificacaoVO>();

	public Set<ProfissionalDeTIVO> getProfissionais() {
		return profissionais;
	}

	public void setProfissionais(Set<ProfissionalDeTIVO> profissionais) {
		this.profissionais = profissionais;
	}

	public Set<QualificacaoVO> getQualificacoesDisponiveisNoJogo() {
		return qualificacoesDisponiveisNoJogo;
	}

	public void setQualificacoesDisponiveisNoJogo(Set<QualificacaoVO> qualificacoesDisponiveisNoJogo) {
		this.qualificacoesDisponiveisNoJogo = qualificacoesDisponiveisNoJogo;
	}
}

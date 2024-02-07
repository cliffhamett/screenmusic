package br.com.alura.screenmusic.model;

public enum GeneroMusical {
    ROCK("rock"),
    HEAVY_METAL("metal"),
    RAP("rap"),
    ELETRONICA("eletrônica"),
    GRUNGE("grunge");

    private String estiloMusical;

    GeneroMusical(String estiloMusical) {
        this.estiloMusical = estiloMusical;
    }

    public static GeneroMusical fromString(String string) {
        for (GeneroMusical generoMusical : GeneroMusical.values()){
            if(generoMusical.estiloMusical.equalsIgnoreCase(string)) {
                return generoMusical;
            }
        }
        throw new IllegalArgumentException("Nenhum gênero musical encontrado para essa string!");
    }
}

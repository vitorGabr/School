package Enums;

public enum NivelCurso {
    GRADUACAO,
    POS_GRADUACAO;

    public static NivelCurso fromString(String s) {
        if (s.equalsIgnoreCase("GRADUACAO")) {
            return GRADUACAO;
        } else if (s.equalsIgnoreCase("POS_GRADUACAO")) {
            return POS_GRADUACAO;
        } else {
            throw new IllegalArgumentException("Tipo de curso inválido: " + s);
        }
    }

    @Override
    public String toString() {
        switch (this) {
            case GRADUACAO:
                return "GRADUACAO";
            case POS_GRADUACAO:
                return "POS_GRADUACAO";
            default:
                throw new IllegalArgumentException();
        }
    }
}
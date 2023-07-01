package Program.func.info;
public enum MethodOC {
    FRIED,
    BOILED,
    FROZEN,
    BAKED,
    STEWED,
    GRILLED,
    ROASTED,
    SAUTEED,
    STEAMED,
    SMOKED,
    POACHED,
    BRAISED,
    BROILED,
    MARINATED;

    public static MethodOC addMethod(String method) {
        switch (method.toLowerCase()) {
            case "fry":
                return FRIED;
            case "boil":
                return BOILED;
            case "freeze":
                return FROZEN;
            case "bake":
                return BAKED;
            case "stew":
                return STEWED;
            case "grill":
                return GRILLED;
            case "roast":
                return ROASTED;
            case "saute":
                return SAUTEED;
            case "steam":
                return STEAMED;
            case "smoke":
                return SMOKED;
            case "poach":
                return POACHED;
            case "braise":
                return BRAISED;
            case "broil":
                return BROILED;
            case "marinate":
                return MARINATED;
            default:
                System.out.println("-");
                return null;
        }
    }
}
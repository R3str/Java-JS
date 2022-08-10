package sample;

public class Country
{
    private String nameCountry;
    private String slug;
    private String ISO2;


    public Country() {
    }

    /**
     * @param nameCountry страна
     * @param slug страна в маленьком регистре без пробелов
     * @param ISO2 двухбуквенный код страны
     */
    public Country(String nameCountry, String slug, String ISO2) {
        super();
        setNameCountry(nameCountry);
        setSlug(slug);
        setISO2(ISO2);
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) { this.slug = slug; }

    public String getISO2() {
        return ISO2;
    }

    public void setISO2(String iSO2) {
        this.ISO2 = iSO2;
    }


    public String toString()
    {
        return "[Страна: " + nameCountry + ", Слаг: " + slug + ", ISO2: " + ISO2 + "]" + System.lineSeparator();
    }
}

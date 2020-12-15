package se.bth.homejungle.storage;

import java.time.LocalDate;

import se.bth.homejungle.storage.dao.CategoryManager;
import se.bth.homejungle.storage.dao.SpeciesManager;
import se.bth.homejungle.storage.entity.Species;
import se.bth.homejungle.storage.entity.SpeciesCategory;

public class DatabaseInitializer {

    private final CategoryManager categoryManager;
    private final SpeciesManager speciesManager;

    public DatabaseInitializer(AppDatabase database) {
        this.categoryManager = database.getCategoryManager();
        this.speciesManager = database.getSpeciesManager();
    }

    public void initializeDatabase() {
        long categoryIdCactus = categoryManager.insert(new SpeciesCategory("cactus", "Cactus and Succulents"));
        long categoryIdHerbs = categoryManager.insert(new SpeciesCategory("herb", "Herbs"));
        long categoryIdBamboo = categoryManager.insert(new SpeciesCategory("bamboo", "Ornamental Grasses and Bamboo"));
        long categoryIdFlower = categoryManager.insert(new SpeciesCategory("flower", "Flower"));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_01",
                "Night Blooming Cereus",
                "Epiphyllum Species, Dutchman's Pipe Cactus, Night Blooming Cereus",
                "Height 2.4-3 m, \nSpacing 45-60 cm, \nEvergreen, \nWhite color bloom, \nBloom time mid spring",
                "Put seeds in to a cup, use normal soil",
                4, 3, 2,
                LocalDate.of(0, 4, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_02",
                "American Aloe",
                "Agave Species, American Century Plant, American Aloe, Maguey",
                "Grown for foliage, \nEvergreen, \nThis plant is fire-retardant, \nSmooth, \nHeight 1.2-1.8 m, \nSpacing, 2.4m-3m",
                "Put seeds in to a cup, use normal soil",
                1, 14, 5,
                LocalDate.of(0, 4, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_03",
                "Velvet Leaf Kalanchoe",
                "Kalanchoe Species, Elephant Ear Kalanchoe, Felt Plant, Velvet Leaf Kalanchoe (Kalanchoe beharensis)",
                "Height 1.2-3.6 m, type Cactus and Succulents, Spacing (90-120 cm), Suitable for growing in containers, Pale Yellow bloom color",
                "Put seeds in to a cup, use normal soil",
                1, 14, 5,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_04",
                "Spiral Aloe",
                "Aloe Species, Spiral Aloe",
                "Evergreen, Height 45-60, Spacing 60-90cm, Bloom time Late summer",
                "Put seeds in to a cup, use normal soil",
                1, 14, 5,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_05",
                "Sticks of Fire 'Rosea'",
                "Euphorbia, Pencil Tree, Milkbush, Milk Bush, Finger Tree, Sticks of Fire 'Rosea'",
                "Height 90-120 cm, Foliage Color: Orange/Apricot Pink/Rose, Can be grown as an annual Suitable for growing in containers, Bloom Color: Red Pale Yellow",
                "Put seeds in to a cup, use normal soil",
                1, 14, 5,
                LocalDate.of(0, 5, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_06",
                "Siebold’s Stonecrop",
                "Hylotelephium Species, October Daphne, Siebold's Sedum, Siebold’s Stonecrop",
                "Height: 15-30 cm, Spacing: 15-22 cm, Bloom Color: Rose/Mauve, Bloom Time: Late Summer/Early Fall, Mid Fall",
                "Put seeds in to a cup, use normal soil",
                1, 14, 5,
                LocalDate.of(0, 8, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_07",
                "Roof House Leek",
                "Sempervivum Species, Hen-and-Chickens, Houseleek, Old Man & Woman, Roof House Leek",
                "Foliage: Grown for foliage Succulent, Height: 15 cm, Spacing: 30-38 cm, Bloom Time: Late Spring/Early Summer",
                "Put seeds in to a cup, use normal soil",
                1, 14, 5,
                LocalDate.of(0, 5, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_08",
                "Stone Plant",
                "Pleiospilos Species, Cleft Stone, Liver Plant, Mimicry Plant, Splitrock, Stone Plant",
                "Height: 15 cm, Spacing: 15-22 cm 22-30 cm, Bloom Color: Orange Bright Yellow, Bloom Time: Late Spring/Early Summer",
                "Put seeds in to a cup, use normal soil",
                1, 14, 4,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_09",
                "Toad Cactus",
                "Ceropegia Species, Starfish Cactus, Toad Cactus",
                "Height: 15 cm, Spacing: 22-30 cm, Bloom Color: Maroon/Burgundy Brown/Bronze, Bloom Time: Mid Summer",
                "Put seeds in to a cup, use normal soil",
                1, 14, 4,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdCactus,
                "cactus_10",
                "Moon Cactus",
                "Gymnocalycium, Moon Cactus, Ruby Ball 'Hibotan",
                "Foliage Color: Blue-Green, Height: 15-30 cm, Spacing:7-15 cm, Bloom Color: Pink, Bloom Time: Mid Spring Late Spring/Early Summer Mid Summer",
                "Put seeds in to a cup, use normal soil",
                3, 7, 3,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdHerbs,
                "herb_01",
                "Lavender Hyssop",
                "Agastache Species, Anise Hyssop, Giant Hyssop, Licorice Mint, Lavender Hyssop",
                "Foliage: Herbaceous Shiny/Glossy, Height 60-90 cm, Spacing 45-60 cm, Bloom Color Medium Purple",
                "Grow outdoors year-round in hardiness zone, Can be grown as an annual, soil 6.1 to 6.5 (mildly acidic)",
                3, 3, 4,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdHerbs,
                "herb_02",
                "Chocolate Mint",
                "Chocolate Mint, Peppermint 'Chocolate",
                "Foliage Shiny/Glossy, Foliage Color Orange/Apricot, Height 30-45 cm, Spacing 45-60 cm, Bloom Color Lavender, Bloom Characteristics This plant is attractive to bees, butterflies and/or birds",
                "Danger Handling plant may cause skin irritation or allergic reaction. Use seed and mildly acidic soil.",
                3, 2, 5,
                LocalDate.of(0, 4, 1)));

        speciesManager.insert(new Species(
                categoryIdHerbs,
                "herb_03",
                "Patchouli",
                "Patchouli",
                "Foliage Grown for foliage Shiny/Glossy, height 45cm -60 cm, Bloom Color White/Near White",
                "acidic or mildly acidic soil, medium size pot",
                3, 1, 4,
                LocalDate.of(0, 8, 1)));

        speciesManager.insert(new Species(
                categoryIdHerbs,
                "herb_04",
                "Dwarf Cranesbil",
                "Geranium, Dwarf Cranesbill, Hardy Geranium 'Biokovo'",
                "Foliage Herbaceous Shiny/Glossy Succulent, Height 15-30 cm, Spacing 30-38 cm 38-45 cm, Bloom Color Pale Pink White/Near White",
                "Any soil, regular cup",
                2, 2, 5,
                LocalDate.of(0, 7, 1)));

        speciesManager.insert(new Species(
                categoryIdHerbs,
                "herb_05",
                "Sweet Basil",
                "Ocimum Species, Common Basil, Sweet Basil",
                "Foliage Shiny/Glossy, Height 30-45 cm, Spacing 22-30 cm, Bloom Color White/Near White",
                "Any soil, regular cup",
                3, 2, 5,
                LocalDate.of(0, 5, 1)));

        speciesManager.insert(new Species(
                categoryIdHerbs,
                "herb_06",
                "Garden Parsley",
                "Petroselinum Species, Curly Parsley, Garden Parsley",
                "Foliage Grown for foliage, Shiny/Glossy",
                "Neutral or acidic soil, medium sized pot, use good seeds",
                3, 2, 4,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdBamboo,
                "bamboo_01",
                "Pony Tails",
                "Nassella Species, Silky Thread Grass, Mexican Feather Grass, Mexican Needle Grass, Pony Tails",
                "Foliage Grown for foliage Herbaceous, Bloom Color Cream/Tan, Bloom Time Late Spring/Early Summer Mid Summer",
                "Use acidic soil, use a large pot, put in the sun",
                3, 2, 5,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdBamboo,
                "bamboo_02",
                "Gardener's Garters",
                "Ribbon Grass, Reed Canary Grass, Gardener's Garters",
                "Foliage Good Fall Color, Foliage Color Blue-Green, Bloom Color Cream/Tan",
                "Use a neutral soil, use a medium size pot, put the seeds in the pot",
                1, 14, 5,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdBamboo,
                "bamboo_03",
                "Variegata",
                "Liriope, Variegated Lily Turf, Lilyturf, Monkey Grass 'Variegata'",
                "Foliage Grown for foliage, Evergreen Good Fall Color, Foliage Color Bronze, Bloom Color Lavender",
                "Use acidic soil, use a medium pot, put in the sun",
                3, 2, 4,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdBamboo,
                "bamboo_04",
                "Monk's Belly Bamboo",
                "Phyllostachys Species, Fairyland Bamboo, Fishpole Bamboo, Golden Bamboo, Monk's Belly Bamboo",
                "Foliage Grown for foliage Evergreen Smooth, Bloom Color Inconspicuous/none",
                "Use neutral soil, big pot, use seeds",
                3, 2, 5,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdBamboo,
                "bamboo_05",
                "Red Spires",
                "Melic Grass 'Red Spires'",
                "Foliage Herbaceous This plant is resistant to deer, Bloom Time Mid Summer, Bloom Color Scarlet (dark red)",
                "Use neutral soil, big pot, use seeds",
                3, 2, 5,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdFlower,
                "flower_01",
                "Blue Daze",
                "Evolvulus, Blue Daze, Hawaiian Blue Eyes 'Blue Daze'",
                "Foliage Succulent, Foliage Color Bronze Blue-Green, Bloom Color Dark Blue",
                "Use acidic soil, small pot, use saplings or seeds",
                3, 2, 5,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdFlower,
                "flower_02",
                "Strawberry Geranium",
                "Saxifraga Species, Creeping Saxifrage, Strawberry Begonia, Strawberry Geranium",
                "Foliage Grown for foliage Evergreen Good Fall Color Succulent Foliage Color Blue-Green, Bloom Color White/Near White",
                "Use neutral soil, small pot, use seeds",
                3, 3, 3,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdFlower,
                "flower_03",
                "Heart Leaf Ice Plant",
                "Mesembryanthemum Species, Baby Sunrose, Dew Plant, Heartleaf Ice Plant, Heart Leaf Ice Plant",
                "Foliage Grown for foliage Evergreen Smooth, Bloom Color Inconspicuous/none, Bloom Time Late Spring/Early Summer",
                "Use alkaline soil, small pot, saplings or seeds",
                3, 2, 5,
                LocalDate.of(0, 3, 1)));

        speciesManager.insert(new Species(
                categoryIdFlower,
                "flower_04",
                "Ornamental Strawberry 'Lipstick'",
                "Fragaria, Ornamental Strawberry 'Lipstick'",
                "Foliage Grown for foliage Evergreen Shiny/Glossy Velvet/Fuzzy",
                "Use acidic soil, small pot, seeds",
                3, 3, 4,
                LocalDate.of(0, 6, 1)));

        speciesManager.insert(new Species(
                categoryIdFlower,
                "flower_05",
                "Pleated Leaf Spathoglottis",
                "Species Orchid, Ground Orchid, Large Purple Orchid, Pleated Leaf Spathoglottis",
                "Foliage Velvet/Fuzzy, Bloom Color Magenta (pink-purple)",
                "Use neutral soil, small pot, use seeds",
                3, 2, 5,
                LocalDate.of(0, 12, 1)));
    }
}

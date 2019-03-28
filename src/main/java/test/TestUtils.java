package test;

import org.kohsuke.randname.RandomNameGenerator;
import test.data.Category;
import test.data.Pet;
import test.data.Status;
import test.data.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by zsmirnova on 27/03/2019.
 */
public class TestUtils {

    public static final RandomNameGenerator RND = new RandomNameGenerator();

    public static String getRandomWord(){
        return RND.next();
    }

    public static String getRandomPath(){ return "/" + getRandomWord();}

    public static Integer getRandomNumber() {
        Random random = new Random();
        return random.nextInt();
    }

    public static Pet generateSimpleTemplate(){
        return Pet.builder()
                .id(Integer.toString(TestUtils.getRandomNumber()))
                .name(TestUtils.getRandomWord())
                .build();
    }

    public static Pet generateFullTemplate() {
        return Pet.builder()
                .id(Integer.toString(TestUtils.getRandomNumber()))
                .name(TestUtils.getRandomWord())
                .photoUrls(Arrays.asList("qwerty", "asdfg"))
                .category(Category.builder().id(Integer.toString(TestUtils.getRandomNumber())).name(TestUtils.getRandomWord()).build())
                .tags(Collections.singletonList(Tag.builder().id(Integer.toString(TestUtils.getRandomNumber())).name(TestUtils.getRandomWord()).build()))
                .status(Status.available)
                .build();
    }

    public static Pet generatePetWithSetFields(Integer petId, String name, List<String> urls, Category category, List<Tag> tags, Status status) {
        return Pet.builder()
                .id(Integer.toString(petId))
                .name(name)
                .photoUrls(urls)
                .category(category)
                .tags(tags)
                .status(status)
                .build();
    }

    public static final String NO_URLS_PET = "{\n" +
            "    \"id\": 11111,\n" +
            "    \"status\": \"available\"\n" +
            "    \"category\": {\n" +
            "        \"id\": -236061256,\n" +
            "        \"name\": \"spatial_hammer\"\n" +
            "    },\n" +
            "    \"name\": \"philosophical_eagle\",\n" +
            "}";

    public static final String INCORRERECT_ID_PET = "{\n" +
            "    \"id\": qqqqqq,\n" +
            "    \"category\": {\n" +
            "        \"id\": -236061256,\n" +
            "        \"name\": \"spatial_hammer\"\n" +
            "    },\n" +
            "    \"name\": \"philosophical_eagle\",\n" +
            "    \"photoUrls\": [\n" +
            "        \"qwerty\",\n" +
            "        \"asdfg\"\n" +
            "    ],\n" +
            "    \"tags\": [\n" +
            "        {\n" +
            "            \"id\": 831590553,\n" +
            "            \"name\": \"written_mask\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"status\": \"available\"\n" +
            "}";

    public static final String INCORRECT_CATEGORY_TAG_PET = "{\n" +
            "    \"id\": 1234567,\n" +
            "    \"category\": {\n" +
            "        \"id\": qqyyy,\n" +
            "        \"name\": \"spatial_hammer\"\n" +
            "    },\n" +
            "    \"name\": \"philosophical_eagle\",\n" +
            "    \"photoUrls\": [\n" +
            "        \"qwerty\",\n" +
            "        \"asdfg\"\n" +
            "    ],\n" +
            "    \"tags\": [\n" +
            "        {\n" +
            "            \"id\": uuuuu,\n" +
            "            \"name\": \"written_mask\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"status\": \"available\"\n" +
            "}";

    public static final String INCORRECT_TAGS_TYPE_PET =
            "{\"id\":311453208," +
                    "\"name\":\"philosophical_eagle\"," +
                    "\"photoUrls\":[\"qwerty\",\"asdfg\"]," +
                    "\"tags\":{\"id\":831590553,\"name\":\"written_mask\"}," +
                    "\"status\":\"available\"}";
}

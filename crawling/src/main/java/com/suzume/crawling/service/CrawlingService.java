package com.suzume.crawling.service;

import com.suzume.crawling.domain.*;
import com.suzume.crawling.repository.*;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CrawlingService {
    private final CoordiRepository coordiRepository;
    private final OuterRepository outerRepository;
    private final PantsRepository pantsRepository;
    private final ShoesRepository shoesRepository;
    private final TopRepository topRepository;

    @Transactional
    public void crawlingData() {
        System.out.println("################### START ###################");

        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920x1080");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        try {
            driver.navigate().to("https://www.musinsa.com/app/codimap/lists");

            driver.findElement(By.cssSelector("body > div.wrap > div.right_area > div.global-filter > button.global-filter__button.global-filter__button--mensinsa")).click();

            int[] categoryNum = {2, 4, 5, 9};
            for (int i = 0; i < categoryNum.length; i++) {
                String mainURL = driver.getCurrentUrl();
                switch (categoryNum[i]) {
                    case 2:
                        System.out.println("################### 캐주얼 코디맵 크롤링 시작 ###################");
                        break;
                    case 4:
                        System.out.println("################### 댄디 코디맵 크롤링 시작 ###################");
                        break;
                    case 5:
                        System.out.println("################### 포멀 코디맵 크롤링 시작 ###################");
                        break;
                    case 9:
                        System.out.println("################### 스트릿 코디맵 크롤링 시작 ###################");
                        break;
                }

                detailCoordi(driver, categoryNum[i], mainURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
            System.out.println("################### END ###################");
        }
    }

    private void detailCoordi(WebDriver driver, int categoryNum, String mainURL) {
        driver.navigate().to(mainURL);

        int num = 2;
        if (categoryNum == 2) num = 3;

        for (int page = 0; page < num; page++) {
            outer:
            for (int i = 3; i <= 12; i++) {
                for (int j = 1; j <= 60; j++) {
                    try {
                        driver.findElement(By.cssSelector("#catelist > div:nth-child(2) > div > dl > dd > ul > li:nth-child(" + categoryNum + ") > a")).click();

                        if (page > 0) {
                            for (int k = 0; k < page; k++) {
                                driver.findElement(By.cssSelector("body > div.wrap > div.right_area > form > div.right_contents.hover_box > div > div.pagination-box.box > div > div > a:nth-child(13)")).click();
                            }
                        }

                        driver.findElement(By.cssSelector("body > div.wrap > div.right_area > form > div.right_contents.hover_box > div > div.pagination-box.box > div > div > a:nth-child(" + i + ")")).click();

                        String styleCategory = driver.findElement(By.cssSelector("#catelist > div:nth-child(2) > div > dl > dd > ul > li:nth-child(" + categoryNum + ") > a")).getText();
                        styleCategory = styleCategory.substring(3);

                        driver.findElement(By.cssSelector("body > div.wrap > div.right_area > form > div.right_contents.hover_box > div > ul > li:nth-child(" + j + ") > div.style-list-item__thumbnail > a")).click();

                        String coordiURL = driver.getCurrentUrl();

                        detailGoods(styleCategory, driver, coordiURL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        driver.navigate().to(mainURL);
                    }
                }
            }
        }
    }

    static Long wholePrice;

    private void detailGoods(String styleCategory, WebDriver driver, String coordiURL) {
        int itemNum = driver.findElements(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div")).size();

        System.out.println("################### 등록되어 있는 코디 상품의 갯수 : " + itemNum + " ###################");

        String img = driver.findElement(By.cssSelector("#style_info > div.codimap-cont > img")).getAttribute("src");

        wholePrice = 0L;

        Coordi coordi = Coordi.builder()
                .img(img)
                .category(styleCategory)
                .price(wholePrice)
                .build();

        coordiRepository.save(coordi);

        for (int i = 1; i <= itemNum; i++) {
            try {
                if (i == 1) {
                    driver.findElement(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div.swiper-slide.style_contents_size.swiper-slide-active > a.brand_item")).click();
                    saveData(driver, coordiURL, coordi);
                } else if (i == 2) {
                    driver.findElement(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div.swiper-slide.style_contents_size.swiper-slide-next > a.brand_item")).click();
                    saveData(driver, coordiURL, coordi);
                } else {
                    driver.findElement(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div:nth-child(" + i + ") > a.brand_item")).click();
                    saveData(driver, coordiURL, coordi);
                }
            } catch (Exception e) {
                e.printStackTrace();

                if (driver.getCurrentUrl().contains("goods")) {
                    driver.navigate().back();

                    if (i == 1) {
                        driver.findElement(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div.swiper-slide.style_contents_size.swiper-slide-active > a.brand_item")).click();
                        saveData(driver, coordiURL, coordi);
                    } else if (i == 2) {
                        driver.findElement(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div.swiper-slide.style_contents_size.swiper-slide-next > a.brand_item")).click();
                        saveData(driver, coordiURL, coordi);
                    } else {
                        driver.findElement(By.cssSelector("#style_info > div.styling_goods.codimap-goods > div > div > div > div.styling_list.swiper-wrapper > div:nth-child(" + i + ") > a.brand_item")).click();
                        saveData(driver, coordiURL, coordi);
                    }
                }
            }
        }

        coordi.updatePrice(wholePrice);
    }

    private void saveData(WebDriver driver, String coordiURL, Coordi coordi) {
        String productInfo = driver.findElement(By.cssSelector("#page_product_detail > div.right_area.page_detail_product > div.right_contents.section_product_summary > div.product_info > p > a:nth-child(1)")).getText();

        try {
            if (productInfo.equals("상의") || productInfo.equals("바지") || productInfo.equals("스니커즈") || productInfo.equals("신발") || productInfo.equals("아우터")) {

                String brand = driver.findElement(By.cssSelector("#page_product_detail > div.right_area.page_detail_product > div.right_contents.section_product_summary > div.product_info > p > a:nth-child(3)")).getText();
                int brandLength = brand.length();
                brand = brand.substring(1, brandLength - 1);

                String category = driver.findElement(By.cssSelector("#page_product_detail > div.right_area.page_detail_product > div.right_contents.section_product_summary > div.product_info > p > a:nth-child(2)")).getText();

                String season;
                try {
                    season = driver.findElement(By.cssSelector("#product_order_info > div.explan_product.product_info_section > ul > li:nth-child(2) > p.product_article_contents > strong")).getText().substring(5);
                    if (season.charAt(0) == 'L') season = "ALL";
                } catch (Exception f) {
                    season = null;
                }

                String item = driver.findElement(By.cssSelector("#page_product_detail > div.right_area.page_detail_product > div.right_contents.section_product_summary > span > em")).getText();

                String tmpprice = driver.findElement(By.cssSelector("#list_price")).getText();
                if (tmpprice.contains("~")) {
                    tmpprice = tmpprice.substring(tmpprice.indexOf("~") + 2);
                    tmpprice = tmpprice.replace(",", "");
                    tmpprice = tmpprice.replace("원", "");
                } else {
                    tmpprice = tmpprice.replace(",", "");
                    tmpprice = tmpprice.replace("원", "");
                }
                Long price = Long.parseLong(tmpprice);

                String img = driver.findElement(By.cssSelector("#bigimg")).getAttribute("src");

                String url = driver.getCurrentUrl();

                if (productInfo.equals("상의")) {
                    Top top = Top.builder()
                            .coordi(coordi)
                            .brand(brand)
                            .category(category)
                            .season(season)
                            .item(item)
                            .price(price)
                            .img(img)
                            .url(url)
                            .build();

                    topRepository.save(top);

                    wholePrice += price;
                } else if (productInfo.equals("바지")) {
                    Pants pants = Pants.builder()
                            .coordi(coordi)
                            .brand(brand)
                            .category(category)
                            .season(season)
                            .item(item)
                            .price(price)
                            .img(img)
                            .url(url)
                            .build();

                    pantsRepository.save(pants);

                    wholePrice += price;
                } else if (productInfo.equals("스니커즈") || productInfo.equals("신발")) {
                    Shoes shoes = Shoes.builder()
                            .coordi(coordi)
                            .brand(brand)
                            .category(category)
                            .season(season)
                            .item(item)
                            .price(price)
                            .img(img)
                            .url(url)
                            .build();

                    shoesRepository.save(shoes);

                    wholePrice += price;
                } else if (productInfo.equals("아우터")) {
                    Outer outer = Outer.builder()
                            .coordi(coordi)
                            .brand(brand)
                            .category(category)
                            .season(season)
                            .item(item)
                            .price(price)
                            .img(img)
                            .url(url)
                            .build();

                    outerRepository.save(outer);

                    wholePrice += price;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.navigate().to(coordiURL);
        }
    }
}
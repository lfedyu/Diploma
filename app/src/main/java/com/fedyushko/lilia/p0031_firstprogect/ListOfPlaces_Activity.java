package com.fedyushko.lilia.p0031_firstprogect;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;


import com.fedyushko.lilia.p0031_firstprogect.TSP.Solver;

import java.util.ArrayList;

public class ListOfPlaces_Activity extends AppCompatActivity {

    public ArrayList<Place> places;
    public ArrayList<Place> fakeRoute;
    private RecyclerView rv;
    Toolbar toolbar;
    DBHelper dbHelper;
    private static final String TAG = "myLogs";

    RVAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Зайшли в клас onCreate");
        dbHelper = new DBHelper(this);
        setContentView(R.layout.activity_list_of_places);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
//        Log.d(TAG,"Ініціалізуємо адаптер");
//        String strPath = TSP(places).toString();
//        Log.d(TAG,"Path:"+strPath);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete(dbHelper.TABLE_PLACES , null, null);
        ContentValues contentValues = new ContentValues(); // as an array with column name and its value
        // for example, column KEY_NAME, and its value that contains in signUpInformation[0]

        for(Place place: places) {
            // Store values at the time of the login attempt.
            contentValues.clear();
            contentValues.put(DBHelper.KEY_PLACE_NAME, place.getName());
            contentValues.put(DBHelper.KEY_PLACE_WIDTH, place.getX());
            contentValues.put(DBHelper.KEY_PLACE_HIEGHT, place.getY());
            database.insert(DBHelper.TABLE_PLACES, null, contentValues);
        }
        dbHelper.close();
    }

    private void initializeData(){


        Place place_1 = new Place("Площа Ринок", "Площа виникла в 14 ст. внаслідок регулярного...", R.drawable.icon_rinok_square, R.drawable.rinok_square,49.841487f,24.031614f);
        Place place_2 = new Place("Палац Потоцьких", "Був побудований у 1880-х як міська резиденція...", R.drawable.icon_pototskiy, R.drawable.pototskiy,49.837961f,24.026720f);
        Place place_3 = new Place("Шевченківський гай", "Музей просто неба, розташований на...", R.drawable.icon_shevchenkivskiy_gay,R.drawable.shevchenkivskiy_gay,49.846392f,24.062377f);
        Place place_4 = new Place("Дім легенд", "Ресторан, у якому кожен зал на семи поверхах...", R.drawable.icon_dim_legend, R.drawable.dim_legend,49.841101f,24.034550f);
        Place place_5 = new Place("Стрийський парк", "Вважається одним із найкрасивіших...", R.drawable.icon_striyskiy_park,R.drawable.icon_striyskiy_park,49.826247f,24.029122f);
        Place place_6 = new Place("Парк ім.Івана Франка", "Розташований біля ЛНУ ім.Івана Франка ...", R.drawable.icon_park_franka, R.drawable.icon_park_franka,49.838618f,24.020707f);
        Place place_7 = new Place("Готель Львів", "Цей заклад зручно розташувався в цетрі...", R.drawable.icon_hotel_lviv,R.drawable.icon_hotel_lviv,49.845962f,24.025022f);
        Place place_8 = new Place("Театр опери та балету", "Національний театр опери та балету ...", R.drawable.icon_opernyy_teatr,  R.drawable.icon_opernyy_teatr,49.844065f,24.026169f);
        Place place_9 = new Place("Домініканський собор", "Домініканський собор і монастир є одним із...", R.drawable.icon_dominicanskiy_sobor, R.drawable.icon_dominicanskiy_sobor,49.842766f,24.034047f);
        Place place_10 = new Place("Музей Арсенал", "Міський Арсенал є найстаріший з трьох ...", R.drawable.icon_arsenal, R.drawable.icon_arsenal,49.841262f, 24.035278f);
        Place place_11 = new Place("Old city hostel", "Розташований в самому серці Львова. Наші ...", R.drawable.icon_hostel, R.drawable.icon_hostel,49.840582f,24.029117f);
        Place place_12 = new Place("Копальня кави", "Єдина в світі копальня кави, де на очах у...", R.drawable.icon_kopalnya_cafe, R.drawable.icon_kopalnya_cafe,49.841705f,24.032753f);
        Place place_13 = new Place("Криївка", "Ресторан-кнайпа у Львові. Відкритий у ...", R.drawable.icon_kriivka, R.drawable.icon_kriivka,49.841294f,24.032270f);
        Place place_14 = new Place("Високий замок", "За архівними документами і ...", R.drawable.icon_visokiy_zamok,  R.drawable.icon_visokiy_zamok,49.848391f,24.037360f);
        Place place_15 = new Place("Готель Жорж", "Найстаріший і найкрасивіший готель ...", R.drawable.icon_hotel_gorg,  R.drawable.icon_hotel_gorg,49.838673f,24.030734f);
        Place place_16 = new Place("Пам'ятник Т.Шевченку", "Знаходиться на проспекті Свободи в...", R.drawable.icon_shevchenko,R.drawable.icon_shevchenko, 49.841065f,24.028258f);
        Place place_17 = new Place("Львівська філармонія", "Львівська філармонія почала роботу у 1902 році...", R.drawable.icon_filarnony, R.drawable.icon_filarnony,49.837609f,24.030136f);
        Place place_18 = new Place("Майстерня шоколаду", " Семиповерхова шоколадна фабрика у самому центрі ...", R.drawable.icon_maiternia_shokoladu,R.drawable.icon_maiternia_shokoladu,49.841160f,24.033205f);
        Place place_19 = new Place("Реберня під Арсеналом", "Тут немає тарілок та столових приборів, ...", R.drawable.icon_rebernya, R.drawable.icon_rebernya,49.841556f,24.035247f);
        Place place_20 = new Place("Собор св.Юрія", "Архикафедральний собор святого Юра у Львові — ...", R.drawable.icon_sobor_svyatogo_yura,  R.drawable.icon_sobor_svyatogo_yura,49.838700f,24.012911f);
        places = new ArrayList<>();
        fakeRoute = new ArrayList<>();
        place_1.setLongInfo("Площа виникла в 14 ст. внаслідок регулярного  планування забудови за взірцем середньовічних західноєвропейських міст, як центр торговельного та суспільного життя міста.Площа має розмір 142 х 129 м, її утворюють 44 будинки і ратуша в центрі. Нумерації будинків не було. ");
        place_2.setLongInfo("Був побудований у 1880-х як міська резиденція.Будівля виконана в стилі французького класицизму, цегляна, обштукатурена, Н-подібна у плані, з розвинутим центральним ризалітом та боковими крилами.Триповерхова, з мансардами. Фасади декоровані фігурними обрамленнями вікон та рустикацією, ліпними консолями балконів та балюстрадами. ");
        place_3.setLongInfo("Музей народної архітектури і побуту імені Климентія Шептицького розташований на лісистих горбах Львівського плато (частина Подільської височини), у північно-східній частині міста, в місцевості Кайзервальд, на території ландшафтного парку «Знесіння». Неподалік (на захід) розташований парк «Високий Замок»." );
        place_4.setLongInfo("Дім легенд” - ресторан, у якому кожен зал на семи поверхах береже певну львівську легенду: час, бруківка, леви, погода... Незвичайні рішення в декорі інтер'єру, славетна тераса на даху, на якій знаходиться старенький автомобіль.Серед кімнат є наприклад, кімната ленди про закуту річку Львова - Полтву. Вона оберігається у кімнаті смороду та колектора.");

        places.add(place_1);
        places.add(place_2);
        places.add(place_3);
        places.add(place_4);
        places.add(place_5);
        places.add(place_6);
        places.add(place_7);
        places.add(place_8);
        places.add(place_9);
        places.add(place_10);
        places.add(place_11);
        places.add(place_12);
        places.add(place_13);
        places.add(place_14);
        places.add(place_15);
        places.add(place_16);
        places.add(place_17);
        places.add(place_18);
        places.add(place_19);
        places.add(place_20);

        fakeRoute.add(place_11);
        fakeRoute.add(place_4);
        fakeRoute.add(place_9);
        fakeRoute.add(place_1);
        fakeRoute.add(place_8);
        fakeRoute.add(place_2);
        fakeRoute.add(place_6);

    }

    private void initializeAdapter(){
        adapter = new RVAdapter( places);
        rv.setAdapter(adapter);

    }


    private int [] TSP (ArrayList <Place> places){

        Solver solver = new Solver(places);
        int[] path = solver.calculate();

        String message = places.get(path[0]).getName();
        for(int i = 1; i < path.length; i++) {
            message += " to " + places.get(path[i]).getName();
        }
        message += " to " + places.get(path[0]).getName();//щоб вернутись в кінцеву
        message += "\nCost: " + solver.getCost();
        Log.d(TAG,"Message"+message);
        return path;
    }



    public ArrayList<Place> getPlaces() {
        return places;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.places_menu, menu);
        return true;
    }
    //little menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.place_show_route) {
           // ArrayList <Place> selected = new ArrayList<Place>();
            //selected.addAll(adapter.getRoute());
            Intent intent = new Intent(this, RouteActivity.class);
            //intent.putParcelableArrayListExtra("route", selected);
            intent.putParcelableArrayListExtra("route", fakeRoute);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}

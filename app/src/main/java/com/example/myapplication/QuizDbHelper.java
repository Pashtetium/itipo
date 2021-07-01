package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;

import com.example.myapplication.QuizContract.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SP_KEY_DB_VER = "db_ver";
    private final Context mContext;
    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
        initialize();
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }


    private void initialize() {
        if (databaseExists()) {
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(mContext);
            int dbVersion = prefs.getInt(SP_KEY_DB_VER, 1);
            if (DATABASE_VERSION != dbVersion) {
                File dbFile = mContext.getDatabasePath(DATABASE_NAME);
            }
        }
    }

    private boolean databaseExists() {
        File dbFile = mContext.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +

                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        Category c1 = new Category("Антивирусы");
        addCategory(c1);
        Category c2 = new Category("Брандмауэр");
        addCategory(c2);
        Category c3 = new Category("Программные закладки");
        addCategory(c3);
        Category c4 = new Category("Клавиатурные шпионы");
        addCategory(c4);
        Category c5 = new Category("ЭЦП и RSA");
        addCategory(c5);
    }

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Что такое компьютерный вирус?",
                "Прикладная программа", "Системная программа",
                "Программа, выполняющая на компьютере несанкционированные действия",
                3, 1);
        addQuestion(q1);

        Question q2 = new Question("Компьютерным вирусом является?",
                "Программа проверки и лечения дисков", "Любая программа, созданная на языках низкого уровня",
                "Программа которая может приписывать себя к другим программам, она обладает способностью \"размножаться\"",
                3, 1);
        addQuestion(q2);

        Question q3 = new Question("Заражение компьютерными вирусами может произойти в процессе ...",
                "Работы с файлами", "Форматирования дискеты",
                "Печати на принтере",
                1, 1);
        addQuestion(q3);

        Question q4 = new Question("Основные типы компьютерных вирусов?",
                "Аппаратные, программные, загрузочные", "Файловые, загрузочные, макровирусы",
                "Файловые, программные, макровирусы",
                2, 1);
        addQuestion(q4);

        Question q5 = new Question("Вход на компьютер по паролю может быть установлен?",
                "в BIOS Setup", "в Internet Explorer",
                "в Word",
                1, 1);
        addQuestion(q5);

        Question q6 = new Question("Какие существуют основные средства защиты?",
                "Резервное копирование наиболее ценных данных", "Аппаратные средства",
                "Программные средства",
                3, 1);
        addQuestion(q6);

        Question q7 = new Question("Какие существуют вспомогательные средства защиты?",
                "Аппаратные средства", "Программные средства",
                "Аппаратные средства и антивирусные программы",
                3, 1);
        addQuestion(q7);

        Question q8 = new Question("На чем основано действие антивирусной программы?",
                "На ожидании начала вирусной атаки", "На сравнении программных кодов с известными вирусами",
                "На удалении зараженных файлов",
                2, 1);
        addQuestion(q8);

        Question q9 = new Question("Какие программы относятся к антивирусным?",
                "AVP, DrWeb, Norton AntiVirus", "MS-DOS, MS Word, AVP",
                "MS Word, MS Excel, Norton Commander",
                1, 1);
        addQuestion(q9);

        Question q10 = new Question("Какую роль выполняют «Трояны»?",
                "Программы", "Троянского коня",
                "Защита",
                2, 1);
        addQuestion(q10);
//////////////////////////////////////////////////////////
        Question q11 = new Question("Как называется брандмауэр на английском?",
                "Grandmauer", "firewall",
                "Brandmauer",
                2, 2);
        addQuestion(q11);

        Question q12 = new Question("Каким образом брандмауэр защищает компьютер?",
                "Ищет вирусы", "Запрещает любые действия",
                "Фильтрует трафик",
                3, 2);
        addQuestion(q12);

        Question q13 = new Question("Какова политика безопасности брандмауэра?",
                "Все, что не разрешено, запрещено", "Все запрещено",
                "Все, что не запрещено, разрешено",
                1, 2);
        addQuestion(q13);

        Question q14 = new Question("Как классифицируется брандмауэр?",
                "Деинсталлятор", "Антивирус",
                "Межсетевой экран",
                3, 2);
        addQuestion(q14);

        Question q15 = new Question("В каком виде могут быть брандмауэры?",
                "Аппаратном и программном", "Только программном",
                "Только аппаратном",
                2, 2);
        addQuestion(q15);

        Question q16 = new Question("Где находится окно управления брандмауэром в Windows?",
                "Панель управления", "Центр поддержки",
                "Мой компьютер",
                1, 2);
        addQuestion(q16);

        Question q17 = new Question("Как открыть брандмауэр через командную строку?",
                "firewall.cpl", "walloffire.cpl",
                "brandmauer.cpl",
                1, 2);
        addQuestion(q17);

        Question q18 = new Question("Как называется компьютер, позволяющий эмулировать выход в сеть?",
                "VPN", "Proxy",
                "Установщик",
                2, 2);
        addQuestion(q18);

        Question q19 = new Question("На каком уровне может функционировать брандмауэр?",
                "Транспортный, прикладной", "Сетевой, транспортный",
                "Сетевой, транспортный, прикладной",
                3, 2);
        addQuestion(q19);

        Question q20 = new Question("Один из основных компонентов брандмауэра?",
                "Проверка битых секторов", "Фильтрация пакетов",
                "Восставновление аварийной дорожки",
                3, 2);
        addQuestion(q20);
/////////////////////////////////////////////////////
        Question q21 = new Question("Сколько типов программных закладок существует?",
                "1", "2",
                "3",
                3, 3);
        addQuestion(q21);

        Question q22 = new Question("Какой из типов программных закладок может вносить произвольные изменения в коды программ?",
                "Первый тип", "Второй тип",
                "Третий тип",
                1, 3);
        addQuestion(q22);

        Question q23 = new Question("Какой из типов программных закладок переносит фрагменты из одной области ОП в другую?",
                "Первый тип", "Второй тип",
                "Третий тип",
                2, 3);
        addQuestion(q23);

        Question q24 = new Question("Какой из типов программных закладок искажает выводимую информацию?",
                "Первый тип", "Второй тип",
                "Третий тип",
                3, 3);
        addQuestion(q24);

        Question q25 = new Question("Классифицируйте закладку, которая ассоциируется с аппаратными средствами компьютера?",
                "Загрузочная", "Драйверная",
                "Программно-аппаратная",
                3, 3);
        addQuestion(q25);

        Question q26 = new Question("Классифицируйте закладку, которая ассоциируется с программами начальной загрузки?",
                "Загрузочная", "Драйверная",
                "Программно-аппаратная",
                1, 3);
        addQuestion(q26);

        Question q27 = new Question("Классифицируйте закладку, которая ассоциируется с драйверами?",
                "Загрузочная", "Драйверная",
                "Программно-аппаратная",
                2, 3);
        addQuestion(q27);

        Question q28 = new Question("Классифицируйте закладку, которая ассоциируется с прикладными программами?",
                "Исполняемая", "Драйверная",
                "Прикладная",
                3, 3);
        addQuestion(q28);

        Question q29 = new Question("Как называют программную закладку, которые все время находятся в ОП?",
                "Резидентные", "Нерезидентные",
                "Постоянные",
                1, 3);
        addQuestion(q29);

        Question q30 = new Question("Как называется закладка, которая являясь частью другой программы, способна втайне причинять ущерб?",
                "Нерезидентная", "Троянская программа",
                "Червь",
                2, 3);
        addQuestion(q30);
///////////////////////////////////////////////////////////////
        Question q31 = new Question("Каким образом работает клавиатурный шпион?",
                "Внедряется в программы", "Перехватывает данные с клавиатуры",
                "Изменяет программы",
                2, 4);
        addQuestion(q31);

        Question q32 = new Question("Типы клавиатурных шпионов?",
                "Трояны, имитаторы, закладки", "Заместители, закладки, шпионы",
                "Имитаторы, фильтры, заместители",
                3, 4);
        addQuestion(q32);

        Question q33 = new Question("Как называется клавиатурный шпион, который внедряет модуль в программу?",
                "Троян", "Имитатор",
                "Фильтр",
                3, 4);
        addQuestion(q33);

        Question q34 = new Question("Как называется клавиатурный шпион, который постоянно охотится за всеми данными, вводимыми с клавиатуры?",
                "Фильтр", "Имитатор",
                "Заместитель",
                1, 4);
        addQuestion(q34);

        Question q35 = new Question("Как называется клавиатурный шпион, который подменяет собой программные модули ОС?",
                "Троян", "Заместитель",
                "Имитатор",
                2, 4);
        addQuestion(q35);

        Question q36 = new Question("К какому типа относятся фильтры?",
                "Резидентные", "Постоянные",
                "Нерезидентные",
                1, 4);
        addQuestion(q36);

        Question q37 = new Question("Как можно защититься от фильтров?",
                "Включить антивирус", "Отключить обработчик событий",
                "Запретить изменение раскладки при вводе пароля",
                3, 4);
        addQuestion(q37);

        Question q38 = new Question("Как можно защититься от имитаторов?",
                "Включить брандмауэр", "Запретить переключение на регистрационное окно",
                "Конфигурировать цепочку модулей",
                2, 4);
        addQuestion(q38);

        Question q39 = new Question("Как можно защититься от заместителей?",
                "Защитить подсистему аутентификации", "Использовать сканеры",
                "Запретить доступ всем, кроме администратора",
                3, 4);
        addQuestion(q39);

        Question q40 = new Question("Самый трудоемкий вид клавиатурных шпионов?",
                "Заместители", "Фильтры",
                "Имитаторы",
                1, 4);
        addQuestion(q40);
/////////////////////////////////////////////
        Question q41 = new Question("Для чего нужна цифровая подпись?",
                "Защита документа от подделки", "Защита от удаления",
                "Защита от вирусов",
                1, 5);
        addQuestion(q41);

        Question q42 = new Question("В каком году был разработан первый Российский стандарт ЭЦП?",
                "В 2002 году", "В 2000 году",
                "В 1994 году",
                3, 5);
        addQuestion(q42);

        Question q43 = new Question("Какие существуют схемы построения ЭЦП?",
                "Симметричные, асимметричные", "Блочные, одиночные",
                "Круговые, квадратные",
                1, 5);
        addQuestion(q43);

        Question q44 = new Question("Когда был разработан алгоритм RSA?",
                "В 1977 году", "В 1976 году",
                "В 1988 году",
                1, 5);
        addQuestion(q44);

        Question q45 = new Question("Когда было впервые предложено понятие \"электронная цифровая подпись\"?",
                "В 1974 году", "В 1976 году",
                "В 1999 году",
                2, 5);
        addQuestion(q45);

        Question q46 = new Question("Кем было предложено понятие ЭЦП?",
                "Уитфилдом Диффи и Мартином Хеллманом", "Сильвио Микали и Рональдом Ривестом",
                "Шафи Гольдвассером и Уитфилдом Диффи",
                1, 5);
        addQuestion(q46);

        Question q47 = new Question("Когда были определены первые требования безопасности цифровой подписи?",
                "В 1981 году", "В 1984 году",
                "В 1979 году",
                2, 5);
        addQuestion(q47);

        Question q48 = new Question("На чем был основан новый стандарт РФ по ЭЦП в 2002 году?",
                "На вычислениях в группе точек эллиптической кривой", "На квантовом преобразовании",
                "На гаммировании",
                1, 5);
        addQuestion(q48);

        Question q49 = new Question("Какие существуют разновидности ЭЦП?",
                "Групповая, доверенная", "Оспоримая, одиночная",
                "Групповая, неоспоримая, доверенная",
                3, 5);
        addQuestion(q49);

        Question q50 = new Question("Самая распространенная криптосистема общего ключа?",
                "Buick", "RSA",
                "WAN",
                2, 5);
        addQuestion(q50);


    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());

        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));

                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));

                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
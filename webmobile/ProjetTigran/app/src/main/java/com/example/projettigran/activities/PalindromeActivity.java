package com.example.projettigran.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projettigran.R;
import com.example.projettigran.component.PalindromeWriter;
import com.example.projettigran.component.infra.IOFileOperations;
import com.example.projettigran.component.PopUpWindowFactory;
import com.example.projettigran.component.RandomPalindromeReader;
import com.example.projettigran.component.infra.ResourceHandler;
import com.example.projettigran.domain.CharacterComparator;
import com.example.projettigran.domain.ComparisonResult;
import com.example.projettigran.domain.StringManipulations;
import com.example.projettigran.services.DisplayColorsService;
import com.example.projettigran.services.ScheduleTaskService;

import java.util.ArrayList;
import java.util.List;

public class PalindromeActivity extends AppCompatActivity {

    public final static String PALINDROME_FILE = "palindrome.txt";
    public final static String NON_PALINDROME_FILE = "non_palindrome.txt";

    private static final String WIKIPEDIA_PALINDROMES = "https://fr.wikipedia.org/wiki/Palindrome";
    private static final String PALINDROME_PEREC_WIKIPEDIA = "https://fr.wikipedia.org/wiki/La_Cl%C3%B4ture_(Perec)";
    private static final String PALINDROME_PEREC = "https://jeretiens.net/palindrome-de-georges-perec-au-moulin-dande/";

    private final ScheduleTaskService scheduleTaskService;
    private final PopUpWindowFactory windowFactory;
    private RandomPalindromeReader randomPalindromeReader;
    private PalindromeWriter palindromeWriter;

    private boolean loadData;

    public PalindromeActivity() {
        super();
        this.scheduleTaskService = ScheduleTaskService.create(DisplayColorsService.create());
        this.windowFactory = PopUpWindowFactory.create();
        this.loadData = true;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palindrome);

        final IOFileOperations ioFileOperations = IOFileOperations.getInstance();
        this.randomPalindromeReader = RandomPalindromeReader.create(ioFileOperations);

        final ResourceHandler resourceHandler = ResourceHandler.from(this);
        this.palindromeWriter = PalindromeWriter.create(resourceHandler, ioFileOperations);
        if (loadData) {
            loadSentences();
        }
    }

    private void loadSentences() {
        palindromeWriter.loadAndWriteIntoFile(this, PALINDROME_FILE, R.raw.palindrome);
        palindromeWriter.loadAndWriteIntoFile(this, NON_PALINDROME_FILE, R.raw.palindrome, R.raw.non_palindrome);
        loadData = false;
    }

    // MENU

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.palindrome_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        final int itemId = item.getItemId();
        if (R.id.a_propos == itemId) {
            final LayoutInflater layoutInflater = LayoutInflater.from(this);
            final View customView = layoutInflater.inflate(R.layout.popup, null);
            final LinearLayout location = findViewById(R.id.linear_layout_palindrome);
            final PopupWindow popUp = windowFactory.manufactor(location, customView);

            System.out.println("HELLO");
        } else if (R.id.item_random_palindrome == itemId) {
            loadRandomSentenceFromFile(PALINDROME_FILE);
        } else if (R.id.item_random_sentence == itemId) {
            loadRandomSentenceFromFile(PALINDROME_FILE, NON_PALINDROME_FILE);
        } else if (R.id.question_palindrome == itemId) {
            onBrowseClick(WIKIPEDIA_PALINDROMES);
        } else if (R.id.perec_palindrome_wiki == itemId) {
            onBrowseClick(PALINDROME_PEREC_WIKIPEDIA);
        } else if (R.id.perec_palindrome == itemId) {
            onBrowseClick(PALINDROME_PEREC);
        }

        return super.onOptionsItemSelected(item);
    }

    private void onBrowseClick(final String url) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(Intent.createChooser(intent, "Browse with"));
    }

    private void loadRandomSentenceFromFile(final String... identifiers) {
        final EditText enterPalindrome = findViewById(R.id.enter_palindrome_editText);
        randomPalindromeReader.fetchRandomSentence(this, identifiers)
                .ifPresent(enterPalindrome::setText);
    }


    // Buttons

    public void clean(final View view) {
        final EditText enterPalindrome = findViewById(R.id.enter_palindrome_editText);
        final String text = StringManipulations.cleaningUpString(Activities.extractText(enterPalindrome));

        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        palindromeView.setText(text);
    }

    public void reverse(final View view) {
        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        final String extractText = Activities.extractText(palindromeView);
        if (extractText.isEmpty()) {
            Toast.makeText(this, "You need to clean first !", Toast.LENGTH_SHORT)
                    .show();
        } else {
            final TextView reversePalindromeView = findViewById(R.id.palindrome_reverse_textView);
            reversePalindromeView.setText(StringManipulations.reverse(extractText));
        }

    }

    public void compare(final View view) {
        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        final String normalText = Activities.extractText(palindromeView);

        final TextView reversePalindromeView = findViewById(R.id.palindrome_reverse_textView);
        final String reversedText = Activities.extractText(reversePalindromeView);

        final int normalTextSize = normalText.length();
        if (normalTextSize != reversedText.length()) {
            Toast.makeText(this, "Texts should be the same size", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        final List<ComparisonResult> colorsToApply = compare(normalText, reversedText);
        scheduleTaskService.schedule(colorsToApply, palindromeView, reversePalindromeView);
    }

    private List<ComparisonResult> compare(final String normal,
                                           final String reversed) {

        final List<ComparisonResult> colorsToApply = new ArrayList<>(normal.length());
        for (int i = 0; i < normal.length(); i++) {
            final ComparisonResult compare = CharacterComparator.compare(normal.charAt(i), reversed.charAt(i));
            colorsToApply.add(compare);
            if (ComparisonResult.RED.equals(compare)) {
                break;
            }
        }
        return colorsToApply;
    }

}
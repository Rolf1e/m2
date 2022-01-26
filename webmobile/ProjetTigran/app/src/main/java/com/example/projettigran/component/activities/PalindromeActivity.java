package com.example.projettigran.component.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.example.projettigran.R;
import com.example.projettigran.component.LoginHolder;
import com.example.projettigran.component.PalindromeWriter;
import com.example.projettigran.component.RandomPalindromeReader;
import com.example.projettigran.component.infra.IOFileOperations;
import com.example.projettigran.component.infra.ResourceHandler;
import com.example.projettigran.component.popups.AProposPopUpDialog;
import com.example.projettigran.component.popups.LoginPopUpDialog;
import com.example.projettigran.domain.CharacterComparator;
import com.example.projettigran.domain.ComparisonResult;
import com.example.projettigran.domain.StringManipulations;
import com.example.projettigran.services.ScheduleTaskService;
import com.example.projettigran.services.colors.AdminDisplayColorsService;
import com.example.projettigran.services.colors.DisplayColorsService;
import com.example.projettigran.services.colors.TestDisplayColorsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PalindromeActivity extends AppCompatActivity {

    public final static String PALINDROME_FILE = "palindrome.txt";
    public final static String NON_PALINDROME_FILE = "non_palindrome.txt";

    private static final String WIKIPEDIA_PALINDROMES = "https://fr.wikipedia.org/wiki/Palindrome";
    private static final String PALINDROME_PEREC_WIKIPEDIA = "https://fr.wikipedia.org/wiki/La_Cl%C3%B4ture_(Perec)";
    private static final String PALINDROME_PEREC = "https://jeretiens.net/palindrome-de-georges-perec-au-moulin-dande/";

    private final LoginHolder loginHolder;
    private final DisplayColorsService adminDisplayColorsService;
    private final DisplayColorsService testDisplayColorService;
    private RandomPalindromeReader randomPalindromeReader;
    private PalindromeWriter palindromeWriter;
    private boolean loadData;

    public PalindromeActivity() {
        super();
        this.adminDisplayColorsService = AdminDisplayColorsService.create();
        this.testDisplayColorService = TestDisplayColorsService.create();
        this.loginHolder = LoginHolder.getInstance();
        this.loadData = true;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (loginHolder.isLogged()) {
            setContentView(R.layout.activity_palindrome_logged);
        } else {
            setContentView(R.layout.activity_palindrome_logged_out);
        }

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
            popUpWindowApropos();
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
        } else if (R.id.login_menu == itemId) {
            if (!loginHolder.isLogged()) {
                popUpLogin();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void popUpLogin() {
        final AppCompatDialogFragment loginPopUp = new LoginPopUpDialog();
        loginPopUp.show(getSupportFragmentManager(), "");
    }

    private void popUpWindowApropos() {
        final AppCompatDialogFragment aProposPopUpDialog = AProposPopUpDialog.create();
        aProposPopUpDialog.show(getSupportFragmentManager(), "");
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

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);
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
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(normalTextSize);

        final ScheduleTaskService scheduleTaskService = ScheduleTaskService.create(adminDisplayColorsService);
        scheduleTaskService.schedule(
                this,
                colorsToApply,
                Arrays.asList(palindromeView, reversePalindromeView),
                progressBar
        );
    }

    public void compareTest(final View view) {
        final EditText enterPalindrome = findViewById(R.id.enter_palindrome_editText);
        final String text = StringManipulations.cleaningUpString(Activities.extractText(enterPalindrome));

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        final TextView palindromeView = findViewById(R.id.palindrome_textView);
        palindromeView.setText(text);

        final String reversedText = StringManipulations.reverse(text);
        final int normalTextSize = text.length();
        if (normalTextSize != reversedText.length()) {
            Toast.makeText(this, "Texts should be the same size", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        final List<ComparisonResult> colorsToApply = compare(text, reversedText);
        progressBar.setMax(normalTextSize);

        final ScheduleTaskService scheduleTaskService = ScheduleTaskService.create(testDisplayColorService);
        scheduleTaskService.schedule(
                this,
                colorsToApply,
                Collections.singleton(palindromeView),
                progressBar
        );
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
using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class CharacterHealth : MonoBehaviour
{
    public const int maxHealth = 3;
    public int currentHealth = maxHealth;
    public GameObject healthBar;
    public AudioClip impact;
    AudioSource audioSource;
    Scene x;

    public void Start()
    {
        audioSource = GetComponent<AudioSource>();

    }

    public void TakeDamage(int amount)
    {
        if(amount>0)
        {

        audioSource.PlayOneShot(impact, .6F);
        }

        currentHealth -= amount;
        if(currentHealth > 3)
        {
            currentHealth = 3;
        }
        healthBar.GetComponent<Image>().fillAmount = currentHealth / 3f;
        if (currentHealth <= 0)
        {
            currentHealth = 0;
            x = SceneManager.GetActiveScene();

            if (x.name.Equals("a1"))
            {
                SceneManager.LoadScene("a1");
            }
            if (x.name.Equals("a2"))
            {
                SceneManager.LoadScene("a2");
            }
            if (x.name.Equals("a3"))
            {
                SceneManager.LoadScene("a3");
            }
            if (x.name.Equals("a4"))
            {
                SceneManager.LoadScene("a4");
            }
            if (x.name.Equals("a5"))
            {
                SceneManager.LoadScene("a5");
            }
        }
    }
}

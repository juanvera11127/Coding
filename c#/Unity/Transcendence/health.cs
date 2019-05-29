using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class health : MonoBehaviour {

    GameObject obj;
    public AudioClip impact;
    AudioSource audioSource;
    // Use this for initialization
    void Start () {
        obj = GetComponent<GameObject>();
        audioSource = GetComponent<AudioSource>();

    }

    // Update is called once per frame
    void Update () {
		
	}
    private void OnTriggerEnter2D(Collider2D collision)
    {
        Debug.Log("HEALTH");
        if (collision.gameObject.tag == "Player1")
        {
            audioSource.Play();
            audioSource.PlayOneShot(impact, .6F);

            collision.gameObject.GetComponent<CharacterHealth>().TakeDamage(-1);
            Destroy(gameObject);
        }
    }
}

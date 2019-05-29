using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class musicCollide : MonoBehaviour {
    public AudioClip impact;
    AudioSource audioSource;
    // Use this for initialization
    void Start () {
        audioSource = GetComponent<AudioSource>();

    }

    // Update is called once per frame

    public void OnCollisionEnter2D(Collision2D collision)
    {

        
            audioSource.PlayOneShot(impact, .6F);
        
        
    }
}

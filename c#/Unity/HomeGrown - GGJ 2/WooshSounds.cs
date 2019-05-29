using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WooshSounds : MonoBehaviour
{
	AudioSource mySound;
	public AudioClip[] sounds;
	// Start is called before the first frame update
	private void Awake()
	{
		mySound = GetComponent<AudioSource>();
	}
	void OnEnable()
    {
		mySound.clip = sounds[Random.Range(0, sounds.Length)];
		mySound.Play();
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}

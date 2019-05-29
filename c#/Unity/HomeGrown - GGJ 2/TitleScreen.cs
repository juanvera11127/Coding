using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
public class TitleScreen : MonoBehaviour
{
	public AudioSource mySound;
	public AudioClip goSound;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
		if (Input.anyKeyDown)
			StartCoroutine(GoTime());
    }
	IEnumerator GoTime()
	{
		mySound.Stop();
		mySound.clip = goSound;
		mySound.loop = false;
		mySound.Play();
		yield return new WaitForSeconds(2);
		SceneManager.LoadScene(1);
	}
}

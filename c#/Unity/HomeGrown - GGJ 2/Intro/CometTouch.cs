using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CometTouch : MonoBehaviour
{
	public GameObject Pulse;
	public Animator anim;
	public GameObject darkMask;
	public GameObject fairy;
	public GameObject fireGlow;
	private bool triggered = false;
    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {

	}

	private void OnTriggerEnter2D(Collider2D other)
	{
		if (other.gameObject.tag == "PlayerAtk" && !triggered)
		{
			triggered = true;
			StartCoroutine(DoThing());
		}
	}

	IEnumerator DoThing()
	{
		anim.SetTrigger("Crack");
		yield return new WaitForSeconds(3.5f);
		Pulse.SetActive(true);
		darkMask.SetActive(false);
		fireGlow.SetActive(false);
		anim.SetTrigger("Cracked");
		GetComponent<AudioSource>().Stop();
		yield return new WaitForSeconds(4f);
		fairy.SetActive(true);
		yield return new WaitForSeconds(1f);
		Pulse.SetActive(false);
	}
}
